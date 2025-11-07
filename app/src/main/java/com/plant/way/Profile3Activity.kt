package com.plant.way

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Profile3Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile3)
        
        setupViews()
    }
    
    private fun setupViews() {
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }
}
