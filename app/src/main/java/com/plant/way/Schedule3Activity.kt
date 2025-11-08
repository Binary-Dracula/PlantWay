package com.plant.way

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Schedule3Activity : AppCompatActivity() {
    
    private lateinit var etPlantName: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule3)
        
        setupViews()
    }
    
    private fun setupViews() {
        etPlantName = findViewById(R.id.et_plant_name)
        
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Image upload area click listener
        findViewById<RelativeLayout>(R.id.rl_image_upload).setOnClickListener {
            Toast.makeText(this, "Select plant image", Toast.LENGTH_SHORT).show()
            // TODO: Open image picker
        }
        
        // OK button click listener
        findViewById<TextView>(R.id.iv_ok_button).setOnClickListener {
            val plantName = etPlantName.text.toString()
            
            if (plantName.isEmpty()) {
                Toast.makeText(this, "Please enter plant name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Plant added successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
