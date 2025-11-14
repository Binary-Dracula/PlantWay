package com.plant.way

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
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
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Community2Activity : AppCompatActivity() {
    
    private lateinit var etTitle: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var ivUploadedImage: ImageView
    private lateinit var ivFlowerIcon: ImageView
    private var capturedImagePath: String? = null
    private var photoUri: Uri? = null
    
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
    
    // 拍照结果
    private val takePicture = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            // 显示拍摄的照片
            ivUploadedImage.setImageURI(photoUri)
            ivUploadedImage.visibility = View.VISIBLE
            ivFlowerIcon.visibility = View.GONE
            // capturedImagePath 已经在 createImageFile() 中设置了，不需要再次设置
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community2)
        
        setupViews()
    }
    
    private fun setupViews() {
        etTitle = findViewById(R.id.et_title)
        etPrice = findViewById(R.id.et_price)
        etDescription = findViewById(R.id.et_description)
        ivUploadedImage = findViewById(R.id.iv_uploaded_image)
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
            addCommunityItem()
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
        val photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.fileprovider",
            photoFile
        )
        takePicture.launch(photoUri)
    }
    
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir("community_images")
        if (!storageDir?.exists()!!) {
            storageDir.mkdirs()
        }
        val imageFile = File(storageDir, "COMMUNITY_${timeStamp}.jpg")
        capturedImagePath = imageFile.absolutePath
        return imageFile
    }
    
    private fun addCommunityItem() {
        val title = etTitle.text.toString().trim()
        val price = etPrice.text.toString().trim()
        val description = etDescription.text.toString().trim()
        
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (price.isEmpty()) {
            Toast.makeText(this, "Please enter a price", Toast.LENGTH_SHORT).show()
            return
        }
        
        // 创建新的社区项目
        val newItem = CommunityItem(
            id = 0, // ID will be assigned by CommunityDataManager
            title = title,
            price = price,
            description = description,
            imageResId = R.drawable.community_2_flower,
            imagePath = capturedImagePath,
            sellerName = "Skyler",
            sellerAvatar = R.drawable.avatar,
            isUserAdded = true
        )
        
        CommunityDataManager.addItem(newItem)
        
        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
