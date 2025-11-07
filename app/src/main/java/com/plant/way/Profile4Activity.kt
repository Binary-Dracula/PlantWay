package com.plant.way

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile4Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile4)
        
        setupViews()
    }
    
    private fun setupViews() {
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Delete button click listener
        findViewById<TextView>(R.id.btn_delete).setOnClickListener {
            Toast.makeText(this, "Delete this product", Toast.LENGTH_SHORT).show()
        }
        
        // Edit button click listener
        findViewById<TextView>(R.id.btn_edit).setOnClickListener {
            Toast.makeText(this, "Edit product description", Toast.LENGTH_SHORT).show()
        }
    }
}
