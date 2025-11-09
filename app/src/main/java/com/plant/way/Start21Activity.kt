package com.plant.way

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Start21Activity : AppCompatActivity() {

    private lateinit var button1: TextView
    private lateinit var button2: TextView
    private lateinit var button3: TextView
    private lateinit var button4: TextView

    private lateinit var item1: ImageView
    private lateinit var item2: ImageView
    private lateinit var item3: ImageView
    private lateinit var item4: ImageView

    private var currentSelectedButton = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start21)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        item1 = findViewById(R.id.item1)
        item2 = findViewById(R.id.item2)
        item3 = findViewById(R.id.item3)
        item4 = findViewById(R.id.item4)
    }

    private fun setupClickListeners() {
        button1.setOnClickListener {
            selectButton(1)
            updateItems(1)
        }

        button2.setOnClickListener {
            selectButton(2)
            updateItems(2)
        }

        button3.setOnClickListener {
            selectButton(3)
            updateItems(3)
        }

        button4.setOnClickListener {
            selectButton(4)
            updateItems(4)
        }
    }

    private fun selectButton(buttonNumber: Int) {
        if (currentSelectedButton == buttonNumber) return

        // Reset all buttons to unselected state
        resetAllButtons()

        // Set selected button
        when (buttonNumber) {
            1 -> {
                button1.setBackgroundResource(R.drawable.bg_start_21_selected)
                button1.setTextColor(resources.getColor(android.R.color.white, null))
            }
            2 -> {
                button2.setBackgroundResource(R.drawable.bg_start_21_selected)
                button2.setTextColor(resources.getColor(android.R.color.white, null))
            }
            3 -> {
                button3.setBackgroundResource(R.drawable.bg_start_21_selected)
                button3.setTextColor(resources.getColor(android.R.color.white, null))
            }
            4 -> {
                button4.setBackgroundResource(R.drawable.bg_start_21_selected)
                button4.setTextColor(resources.getColor(android.R.color.white, null))
            }
        }

        currentSelectedButton = buttonNumber
    }

    private fun resetAllButtons() {
        val unselectedColor = resources.getColor(android.R.color.darker_gray, null)
        
        button1.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button1.setTextColor(unselectedColor)
        
        button2.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button2.setTextColor(unselectedColor)
        
        button3.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button3.setTextColor(unselectedColor)
        
        button4.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button4.setTextColor(unselectedColor)
    }

    private fun updateItems(buttonNumber: Int) {
        when (buttonNumber) {
            1 -> {
                // Balcony fruits
                item1.setImageResource(R.drawable.bg_start_21_item1)
                item2.setImageResource(R.drawable.bg_start_21_item2)
                item3.setImageResource(R.drawable.bg_start_21_item3)
                item4.setImageResource(R.drawable.bg_start_21_item4)
            }
            2 -> {
                // Balcony vegetables
                item1.setImageResource(R.drawable.bg_start_21_item5)
                item2.setImageResource(R.drawable.bg_start_21_item6)
                item3.setImageResource(R.drawable.bg_start_21_item7)
                item4.setImageResource(R.drawable.bg_start_21_item8)
            }
            3 -> {
                // Air-purifying plants
                item1.setImageResource(R.drawable.bg_start_21_item9)
                item2.setImageResource(R.drawable.bg_start_21_item10)
                item3.setImageResource(R.drawable.bg_start_21_item11)
                item4.setImageResource(R.drawable.bg_start_21_item12)
            }
            4 -> {
                // Ornamental plants
                item1.setImageResource(R.drawable.bg_start_21_item13)
                item2.setImageResource(R.drawable.bg_start_21_item14)
                item3.setImageResource(R.drawable.bg_start_21_item15)
                item4.setImageResource(R.drawable.bg_start_21_item16)
            }
        }
    }
}
