package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    
    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        inputUsername = findViewById(R.id.input_username)
        inputPassword = findViewById(R.id.input_password)

        findViewById<View?>(R.id.btn_login)?.setOnClickListener {
            performLogin()
        }
    }
    
    private fun performLogin() {
        val username = inputUsername.text.toString().trim()
        val password = inputPassword.text.toString().trim()
        
        // 验证用户名
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter username or email", Toast.LENGTH_SHORT).show()
            inputUsername.requestFocus()
            return
        }
        
        // 验证密码
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            inputPassword.requestFocus()
            return
        }
        
        // 两个都不为空，登录成功
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish() // 关闭登录页面，防止用户返回
    }
}