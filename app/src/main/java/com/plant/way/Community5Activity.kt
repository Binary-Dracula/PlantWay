package com.plant.way

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Community5Activity : AppCompatActivity() {
    
    private lateinit var etMessage: EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community5)
        
        setupViews()
    }
    
    private fun setupViews() {
        etMessage = findViewById(R.id.et_message)
        
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Message input click listener
        etMessage.setOnClickListener {
            Toast.makeText(this, "Type your message", Toast.LENGTH_SHORT).show()
        }
    }
}
