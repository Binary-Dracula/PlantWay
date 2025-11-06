package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<View?>(R.id.btn_login)?.setOnClickListener { v: View? ->
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}