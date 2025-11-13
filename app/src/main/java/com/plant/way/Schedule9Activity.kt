package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Schedule9Activity : AppCompatActivity() {
    
    private lateinit var plantAdapter: PlantAdapter
    private lateinit var rvPlantsList: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule9)
        
        setupViews()
        setupRecyclerView()
    }
    
    override fun onResume() {
        super.onResume()
        // 每次返回时刷新数据
        refreshPlantList()
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
    }
    
    private fun setupRecyclerView() {
        rvPlantsList = findViewById(R.id.rv_plants_list)
        rvPlantsList.layoutManager = LinearLayoutManager(this)
        
        // 从单例类获取数据
        val plantList = PlantDataManager.getPlantList()
        
        // 设置适配器
        plantAdapter = PlantAdapter(
            plantList = plantList,
            onItemClick = { plant ->
                // 点击事件处理 - 跳转到Schedule7Activity并传递植物ID
                val intent = Intent(this, Schedule7Activity::class.java)
                intent.putExtra(Schedule7Activity.EXTRA_PLANT_ID, plant.id)
                startActivity(intent)
            },
            onItemLongClick = { plant ->
                // 长按事件处理 - 显示删除确认对话框
                showDeleteConfirmDialog(plant)
            }
        )
        
        rvPlantsList.adapter = plantAdapter
    }
    
    private fun refreshPlantList() {
        // 刷新数据
        val plantList = PlantDataManager.getPlantList()
        plantAdapter = PlantAdapter(
            plantList = plantList,
            onItemClick = { plant ->
                // 点击事件处理 - 跳转到Schedule7Activity并传递植物ID
                val intent = Intent(this, Schedule7Activity::class.java)
                intent.putExtra(Schedule7Activity.EXTRA_PLANT_ID, plant.id)
                startActivity(intent)
            },
            onItemLongClick = { plant ->
                // 长按事件处理 - 显示删除确认对话框
                showDeleteConfirmDialog(plant)
            }
        )
        rvPlantsList.adapter = plantAdapter
    }
    
    private fun showDeleteConfirmDialog(plant: PlantItem) {
        AlertDialog.Builder(this)
            .setTitle("Delete Plant")
            .setMessage("Are you sure you want to delete ${plant.name}? This will also delete all associated tasks.")
            .setPositiveButton("Confirm") { dialog, _ ->
                // 确认删除
                PlantDataManager.removePlant(plant.id)
                refreshPlantList()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // 取消
                dialog.dismiss()
            }
            .show()
    }
}
