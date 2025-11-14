package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Profile2Activity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteAdapter
    private lateinit var deleteConfirmationHelper: DeleteConfirmationHelper
    private val favoriteChangeListener: () -> Unit = {
        updateData()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile2)
        
        deleteConfirmationHelper = DeleteConfirmationHelper(findViewById(android.R.id.content))
        
        setupViews()
        setupRecyclerView()
        
        // 注册收藏数据变化监听
        CommunityDataManager.addFavoriteChangeListener(favoriteChangeListener)
    }
    
    private fun setupViews() {
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_favorites)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        adapter = FavoriteAdapter(
            items = CommunityDataManager.getFavoriteList(),
            onItemClick = { item ->
                // 点击item跳转到详情页
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
            title = "Remove from Favorites",
            message = "Are you sure you want to remove ${item.title} from favorites?",
            onConfirm = {
                CommunityDataManager.removeFromFavorite(item.id)
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
            }
        )
    }
    
    private fun updateData() {
        adapter.updateData(CommunityDataManager.getFavoriteList())
    }
    
    override fun onResume() {
        super.onResume()
        updateData()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        CommunityDataManager.removeFavoriteChangeListener(favoriteChangeListener)
    }
}
