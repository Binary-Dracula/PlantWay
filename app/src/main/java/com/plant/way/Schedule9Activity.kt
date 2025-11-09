package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Schedule9Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule9)
        
        setupViews()
    }
    
    private fun setupViews() {
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Add button click listener
        findViewById<ImageView>(R.id.iv_add_button).setOnClickListener {
            startActivity(Intent(this, Schedule3Activity::class.java))
        }
        
        // Lemon item click listener
        findViewById<LinearLayout>(R.id.ll_item_lemon).setOnClickListener {
            startActivity(Intent(this, Schedule7Activity::class.java))
        }
        
        // Basil item click listener
        findViewById<LinearLayout>(R.id.ll_item_basil).setOnClickListener {

        }
    }
}
