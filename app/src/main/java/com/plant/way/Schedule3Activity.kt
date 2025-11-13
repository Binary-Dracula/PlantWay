package com.plant.way

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Schedule3Activity : AppCompatActivity() {
    
    private lateinit var etPlantName: EditText
    private lateinit var ivFlowerIcon: ImageView
    private var capturedImageUri: Uri? = null
    private var photoFile: File? = null
    
    // 相机权限请求
    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show()
        }
    }
    
    // 拍照结果处理
    private val takePicture = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && capturedImageUri != null) {
            // 显示拍摄的图片
            ivFlowerIcon.setImageURI(capturedImageUri)
            ivFlowerIcon.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule3)
        
        setupViews()
    }
    
    private fun setupViews() {
        etPlantName = findViewById(R.id.et_plant_name)
        ivFlowerIcon = findViewById(R.id.iv_flower_icon)
        
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Image upload area click listener
        findViewById<RelativeLayout>(R.id.rl_image_upload).setOnClickListener {
            checkCameraPermissionAndOpen()
        }
        
        // OK button click listener
        findViewById<TextView>(R.id.iv_ok_button).setOnClickListener {
            addPlant()
        }
    }
    
    private fun checkCameraPermissionAndOpen() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }
            else -> {
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }
    
    private fun openCamera() {
        try {
            // 创建临时文件存储照片
            photoFile = createImageFile()
            photoFile?.let { file ->
                capturedImageUri = FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    file
                )
                takePicture.launch(capturedImageUri)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error opening camera: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(null)
        return File.createTempFile(
            "PLANT_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }
    
    private fun addPlant() {
        val plantName = etPlantName.text.toString().trim()
        
        if (plantName.isEmpty()) {
            Toast.makeText(this, "Please enter plant name", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (capturedImageUri == null) {
            Toast.makeText(this, "Please take a photo of the plant", Toast.LENGTH_SHORT).show()
            return
        }
        
        // 生成新的植物ID
        val newId = PlantDataManager.getPlantList().maxOfOrNull { it.id }?.plus(1) ?: 1
        
        // 获取当前日期
        val currentDate = SimpleDateFormat("yyyy MM dd", Locale.getDefault()).format(Date())
        
        // 创建新的植物对象
        val newPlant = PlantItem(
            id = newId,
            name = plantName,
            joinTime = "Join time",
            date = currentDate,
            imageUri = capturedImageUri.toString()
        )
        
        // 添加到数据管理器
        PlantDataManager.addPlant(newPlant)
        
        Toast.makeText(this, "Plant added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
