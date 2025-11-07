package com.plant.way

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
        
        // Add button click listener
        findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            Toast.makeText(this, "Add new message", Toast.LENGTH_SHORT).show()
        }
        
        // Message item click listener
        findViewById<LinearLayout>(R.id.ll_message_item).setOnClickListener {
            Toast.makeText(this, "Open conversation with SASA", Toast.LENGTH_SHORT).show()
        }
    }
}
