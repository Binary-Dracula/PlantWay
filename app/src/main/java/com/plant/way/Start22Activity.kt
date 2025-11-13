package com.plant.way

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Start22Activity : AppCompatActivity() {

    private lateinit var topImage: ImageView
    private lateinit var plantName: TextView
    private lateinit var wateringText: TextView
    private lateinit var fertilizingText: TextView
    private lateinit var pruningText: TextView
    private lateinit var pestAndDiseaseText: TextView
    private lateinit var lightAndTemperatureText: TextView
    private lateinit var soilRequirementsText: TextView
    private lateinit var joinGardenButton: TextView
    
    private var currentPlantDetail: PlantDetailEntity? = null

    companion object {
        const val EXTRA_PLANT_DETAIL = "extra_plant_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start22)

        initViews()
        setupData()
        setupClickListeners()
    }

    private fun initViews() {
        topImage = findViewById(R.id.topImage)
        plantName = findViewById(R.id.plantName)
        wateringText = findViewById(R.id.wateringText)
        fertilizingText = findViewById(R.id.fertilizingText)
        pruningText = findViewById(R.id.pruningText)
        pestAndDiseaseText = findViewById(R.id.pestAndDiseaseText)
        lightAndTemperatureText = findViewById(R.id.lightAndTemperatureText)
        soilRequirementsText = findViewById(R.id.soilRequirementsText)
        joinGardenButton = findViewById(R.id.joinGardenButton)

        // Setup back button
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }

    private fun setupData() {
        val plantDetail = intent.getParcelableExtra<PlantDetailEntity>(EXTRA_PLANT_DETAIL)
        
        plantDetail?.let { detail ->
            currentPlantDetail = detail
            topImage.setImageResource(detail.topImageResource)
            plantName.text = detail.plantName
            wateringText.text = detail.wateringText
            fertilizingText.text = detail.fertilizingText
            pruningText.text = detail.pruningText
            pestAndDiseaseText.text = detail.pestAndDiseasePreventionText
            lightAndTemperatureText.text = detail.lightAndTemperatureText
            soilRequirementsText.text = detail.soilRequirementsText
        }
    }

    private fun setupClickListeners() {
        joinGardenButton.setOnClickListener {
            addPlantToGarden()
        }
    }
    
    private fun addPlantToGarden() {
        currentPlantDetail?.let { detail ->
            // 生成新的植物ID
            val newId = PlantDataManager.getPlantList().maxOfOrNull { it.id }?.plus(1) ?: 1
            
            // 获取当前日期
            val currentDate = SimpleDateFormat("yyyy MM dd", Locale.getDefault()).format(Date())
            
            // 创建新的植物对象
            val newPlant = PlantItem(
                id = newId,
                name = detail.plantName,
                joinTime = "Join time",
                date = currentDate,
                imageResId = detail.topImageResource
            )
            
            // 添加到数据管理器
            PlantDataManager.addPlant(newPlant)
            
            Toast.makeText(this, "${detail.plantName} added to your garden!", Toast.LENGTH_SHORT).show()
            // 不关闭页面，让用户继续查看植物详情
        } ?: run {
            Toast.makeText(this, "No plant data available", Toast.LENGTH_SHORT).show()
        }
    }
}