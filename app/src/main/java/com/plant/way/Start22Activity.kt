package com.plant.way

import android.os.Bundle

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
            // TODO: 实现加入花园功能
            finish()
        }
    }
}