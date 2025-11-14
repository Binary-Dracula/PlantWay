package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Profile3Activity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileCommunityAdapter
    private lateinit var deleteConfirmationHelper: DeleteConfirmationHelper
    private val dataChangeListener: () -> Unit = {
        updateData()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile3)
        
        deleteConfirmationHelper = DeleteConfirmationHelper(findViewById(android.R.id.content))
        
        setupViews()
        setupRecyclerView()
        
        // 注册数据变化监听
        CommunityDataManager.addDataChangeListener(dataChangeListener)
    }
    
    private fun setupViews() {
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Add button click listener
        findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            startActivity(Intent(this, Community2Activity::class.java))
        }
    }
    
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_profile_items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        adapter = ProfileCommunityAdapter(
            items = CommunityDataManager.getUserAddedItems(),
            onItemClick = { item ->
                // 点击item跳转到详情页，传递item ID
                val intent = Intent(this, Community3Activity::class.java)
                intent.putExtra("ITEM_ID", item.id)
                startActivity(intent)
            },
            onItemLongClick = { item ->
                showDeleteConfirmation(item)
            }
        )
        recyclerView.adapter = adapter
    }
    
    private fun showDeleteConfirmation(item: CommunityItem) {
        deleteConfirmationHelper.show(
            title = "Delete Item",
            message = "Are you sure you want to delete ${item.title}?",
            onConfirm = {
                CommunityDataManager.removeItem(item.id)
                Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show()
            }
        )
    }
    
    private fun updateData() {
        adapter.updateData(CommunityDataManager.getUserAddedItems())
    }
    
    override fun onResume() {
        super.onResume()
        updateData()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        CommunityDataManager.removeDataChangeListener(dataChangeListener)
    }
}
