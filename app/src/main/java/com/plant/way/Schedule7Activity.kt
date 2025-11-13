package com.plant.way

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class Schedule7Activity : AppCompatActivity() {
    
    private lateinit var ivPlantImage: ImageView
    private lateinit var tvPlantName: TextView
    private lateinit var rvCareEvents: RecyclerView
    private lateinit var adapter: PlantTaskListAdapter
    private lateinit var viewOverlay: View
    private lateinit var llPopMenu: LinearLayout
    private val plantTasks = mutableListOf<PlantTask>()
    private var currentPlantId: Int = -1
    private var currentPlant: PlantItem? = null
    
    companion object {
        const val EXTRA_PLANT_ID = "extra_plant_id"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule7)
        
        // 获取传入的植物ID
        currentPlantId = intent.getIntExtra(EXTRA_PLANT_ID, -1)
        
        if (currentPlantId == -1) {
            // 如果没有传入ID，使用默认的第一个植物（向后兼容）
            currentPlant = PlantDataManager.getPlantList().firstOrNull()
            currentPlantId = currentPlant?.id ?: -1
        } else {
            currentPlant = PlantDataManager.getPlantList().find { it.id == currentPlantId }
        }
        
        if (currentPlant == null) {
            Toast.makeText(this, "Plant not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupViews()
        loadPlantTasks()
        setupRecyclerView()
        setupMenuListeners()
    }
    
    private fun setupViews() {
        ivPlantImage = findViewById(R.id.iv_lemon)
        tvPlantName = findViewById(R.id.tv_plant_name)
        viewOverlay = findViewById(R.id.view_overlay)
        llPopMenu = findViewById(R.id.ll_pop_menu)
        
        // 设置植物图片和名称
        currentPlant?.let { plant ->
            // 设置植物名称
            tvPlantName.text = plant.name
            
            // 设置植物图片 - 优先显示用户拍摄的图片
            if (plant.imageUri != null) {
                ivPlantImage.setImageURI(Uri.parse(plant.imageUri))
            } else if (plant.imageResId != 0) {
                ivPlantImage.setImageResource(plant.imageResId)
            }
        }
        
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
    
    private fun loadPlantTasks() {
        // 从当前植物加载所有任务
        currentPlant?.tasks?.let { tasks ->
            plantTasks.addAll(tasks)
        }
    }
    
    private fun setupRecyclerView() {
        rvCareEvents = findViewById(R.id.rv_care_events)
        adapter = PlantTaskListAdapter(
            tasks = plantTasks,
            onTaskLongClick = { task ->
                // 长按事件处理 - 显示删除确认对话框
                showDeleteTaskConfirmDialog(task)
            }
        )
        rvCareEvents.layoutManager = LinearLayoutManager(this)
        rvCareEvents.adapter = adapter
    }
    
    private fun setupMenuListeners() {
        // Watering
        findViewById<LinearLayout>(R.id.ll_menu_watering).setOnClickListener {
            addPlantTask(TaskType.WATERING)
            hideMenu()
        }
        
        // Fertilizing
        findViewById<LinearLayout>(R.id.ll_menu_fertilizing).setOnClickListener {
            addPlantTask(TaskType.FERTILIZING)
            hideMenu()
        }
        
        // Pruning
        findViewById<LinearLayout>(R.id.ll_menu_pruning).setOnClickListener {
            addPlantTask(TaskType.PRUNING)
            hideMenu()
        }
        
        // Pest control (映射到 REPOTTING)
        findViewById<LinearLayout>(R.id.ll_menu_pest_control).setOnClickListener {
            addPlantTask(TaskType.REPOTTING)
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
    
    private fun addPlantTask(taskType: TaskType) {
        val dateFormat = SimpleDateFormat("yyyy MM dd", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        
        // 添加到PlantDataManager并获取新创建的任务
        PlantDataManager.addTaskToPlant(currentPlantId, taskType, currentDate)
        
        // 获取刚添加的任务
        val newTask = currentPlant?.tasks?.lastOrNull()
        if (newTask != null) {
            adapter.addTask(newTask)
            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showDeleteTaskConfirmDialog(task: PlantTask) {
        val taskTypeName = when (task.taskType) {
            TaskType.WATERING -> "Watering"
            TaskType.FERTILIZING -> "Fertilizing"
            TaskType.PRUNING -> "Pruning"
            TaskType.REPOTTING -> "Repotting"
        }
        
        AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete this $taskTypeName task (${task.taskDate})?")
            .setPositiveButton("Confirm") { dialog, _ ->
                // 确认删除
                PlantDataManager.removeTask(currentPlantId, task.id)
                adapter.removeTask(task)
                Toast.makeText(this, "Task deleted successfully", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // 取消
                dialog.dismiss()
            }
            .show()
    }
}
