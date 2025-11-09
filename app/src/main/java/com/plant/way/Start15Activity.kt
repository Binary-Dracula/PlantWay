package com.plant.way

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Start15Activity : AppCompatActivity() {

    private lateinit var button1: TextView
    private lateinit var button2: TextView
    private lateinit var content1: LinearLayout
    private lateinit var content2: LinearLayout
    private lateinit var joinGardenButton: TextView
    private lateinit var bottomPop: View

    private var currentSelectedButton = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start15)

        initViews()
        setupClickListeners()
        showContent(1)
    }

    private fun initViews() {
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        content1 = findViewById(R.id.content1)
        content2 = findViewById(R.id.content2)
        joinGardenButton = findViewById(R.id.joinGardenButton)
        bottomPop = findViewById(R.id.bottom_pop)

        // Initially hide bottom_pop
        bottomPop.visibility = View.GONE

        // Setup back button
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }

    private fun setupClickListeners() {
        button1.setOnClickListener {
            selectButton(1)
            showContent(1)
        }

        button2.setOnClickListener {
            selectButton(2)
            showContent(2)
        }

        joinGardenButton.setOnClickListener {
            toggleBottomPop()
        }
    }

    private fun selectButton(buttonNumber: Int) {
        if (currentSelectedButton == buttonNumber) return

        when (buttonNumber) {
            1 -> {
                button1.setBackgroundResource(R.drawable.bg_start_21_selected)
                button1.setTextColor(resources.getColor(android.R.color.white, null))
                button2.setBackgroundResource(R.drawable.bg_start_21_unselected)
                button2.setTextColor(android.graphics.Color.parseColor("#56E4D3"))
            }
            2 -> {
                button2.setBackgroundResource(R.drawable.bg_start_21_selected)
                button2.setTextColor(resources.getColor(android.R.color.white, null))
                button1.setBackgroundResource(R.drawable.bg_start_21_unselected)
                button1.setTextColor(android.graphics.Color.parseColor("#56E4D3"))
            }
        }

        currentSelectedButton = buttonNumber
    }

    private fun showContent(contentNumber: Int) {
        when (contentNumber) {
            1 -> {
                content1.visibility = View.VISIBLE
                content2.visibility = View.GONE
            }
            2 -> {
                content1.visibility = View.GONE
                content2.visibility = View.VISIBLE
            }
        }
    }

    private fun toggleBottomPop() {
        if (bottomPop.visibility == View.VISIBLE) {
            bottomPop.visibility = View.GONE
        } else {
            bottomPop.visibility = View.VISIBLE
        }
    }
}