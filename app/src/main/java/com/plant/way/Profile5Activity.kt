package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile5Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile5)
        
        setupViews()
    }
    
    private fun setupViews() {
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Add button click listener - navigate to Community2Activity
        findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            startActivity(Intent(this, Community2Activity::class.java))
        }
        
        // Message item click listener - navigate to Community5Activity
        findViewById<LinearLayout>(R.id.ll_message_item).setOnClickListener {
            startActivity(Intent(this, Community5Activity::class.java))
        }
    }
}
