package com.plant.way

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class Schedule7Activity : AppCompatActivity() {
    
    private lateinit var rvCareEvents: RecyclerView
    private lateinit var adapter: CareEventAdapter
    private lateinit var viewOverlay: View
    private lateinit var llPopMenu: LinearLayout
    private val careEvents = mutableListOf<CareEvent>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule7)
        
        setupViews()
        setupRecyclerView()
        setupMenuListeners()
    }
    
    private fun setupViews() {
        viewOverlay = findViewById(R.id.view_overlay)
        llPopMenu = findViewById(R.id.ll_pop_menu)
        
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Add button click listener
        findViewById<LinearLayout>(R.id.ll_add_button).setOnClickListener {
            showMenu()
        }
        
        // Overlay click listener to hide menu
        viewOverlay.setOnClickListener {
            hideMenu()
        }
    }
    
    private fun setupRecyclerView() {
        rvCareEvents = findViewById(R.id.rv_care_events)
        adapter = CareEventAdapter(careEvents)
        rvCareEvents.layoutManager = LinearLayoutManager(this)
        rvCareEvents.adapter = adapter
    }
    
    private fun setupMenuListeners() {
        // Watering
        findViewById<LinearLayout>(R.id.ll_menu_watering).setOnClickListener {
            addCareEvent(CareEvent.TYPE_WATERING, R.drawable.ic_schedule_7_1)
            hideMenu()
        }
        
        // Fertilizing
        findViewById<LinearLayout>(R.id.ll_menu_fertilizing).setOnClickListener {
            addCareEvent(CareEvent.TYPE_FERTILIZING, R.drawable.ic_schedule_7_2)
            hideMenu()
        }
        
        // Pruning
        findViewById<LinearLayout>(R.id.ll_menu_pruning).setOnClickListener {
            addCareEvent(CareEvent.TYPE_PRUNING, R.drawable.ic_schedule_7_3)
            hideMenu()
        }
        
        // Pest control
        findViewById<LinearLayout>(R.id.ll_menu_pest_control).setOnClickListener {
            addCareEvent(CareEvent.TYPE_PEST_CONTROL, R.drawable.ic_schedule_7_4)
            hideMenu()
        }
    }
    
    private fun showMenu() {
        viewOverlay.visibility = View.VISIBLE
        llPopMenu.visibility = View.VISIBLE
    }
    
    private fun hideMenu() {
        viewOverlay.visibility = View.GONE
        llPopMenu.visibility = View.GONE
    }
    
    private fun addCareEvent(title: String, iconRes: Int) {
        val dateFormat = SimpleDateFormat("yyyy MM dd", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        
        val event = CareEvent(
            title = title,
            time = currentDate,
            iconRes = iconRes
        )
        
        adapter.addEvent(event)
    }
}
