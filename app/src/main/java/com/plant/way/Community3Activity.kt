package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class Community3Activity : AppCompatActivity() {
    
    private lateinit var vpImages: ViewPager2
    private lateinit var tvLikeCount: TextView
    private lateinit var tvFavoriteCount: TextView
    private lateinit var tvSellerName: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivAvatar: ImageView
    private lateinit var ivFavorite: ImageView
    
    private var likeCount = 1
    private var favoriteCount = 1
    private var isLiked = false
    private var currentItem: CommunityItem? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community3)
        
        loadItemData()
        setupViews()
    }
    
    private fun loadItemData() {
        val itemId = intent.getIntExtra("ITEM_ID", -1)
        if (itemId != -1) {
            currentItem = CommunityDataManager.getCommunityList().find { it.id == itemId }
        }
    }
    
    private fun setupViews() {
        vpImages = findViewById(R.id.vp_images)
        tvLikeCount = findViewById(R.id.tv_like_count)
        tvFavoriteCount = findViewById(R.id.tv_favorite_count)
        tvSellerName = findViewById(R.id.tv_seller_name)
        tvProductName = findViewById(R.id.tv_product_name)
        tvPrice = findViewById(R.id.tv_price)
        tvDescription = findViewById(R.id.tv_description)
        ivAvatar = findViewById(R.id.iv_avatar)
        ivFavorite = findViewById(R.id.iv_favorite)
        
        // Display item data
        currentItem?.let { item ->
            tvSellerName.text = item.sellerName
            tvProductName.text = item.title
            tvPrice.text = "$${item.price}"
            tvDescription.text = item.description
            ivAvatar.setImageResource(item.sellerAvatar)
            
            // Setup ViewPager with item image
            val images = if (!item.imagePath.isNullOrEmpty()) {
                listOf(item.imagePath)
            } else {
                listOf(item.imageResId)
            }
            vpImages.adapter = ImagePagerAdapter(images, item.imagePath != null)
        } ?: run {
            // Fallback to default images if no item found
            val images = listOf(R.drawable.img_tool_1, R.drawable.img_tool_2)
            vpImages.adapter = ImagePagerAdapter(images, false)
        }
        
        // Back button click listener
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        
        // Like button click listener
        findViewById<LinearLayout>(R.id.ll_like).setOnClickListener {
            isLiked = !isLiked
            if (isLiked) {
                likeCount++
                Toast.makeText(this, "Liked", Toast.LENGTH_SHORT).show()
            } else {
                likeCount--
                Toast.makeText(this, "Unliked", Toast.LENGTH_SHORT).show()
            }
            tvLikeCount.text = likeCount.toString()
        }
        
        // 初始化收藏状态
        updateFavoriteIcon()
        
        // Favorite button click listener
        findViewById<LinearLayout>(R.id.ll_favorite).setOnClickListener {
            currentItem?.let { item ->
                if (CommunityDataManager.isFavorite(item.id)) {
                    // 已收藏，取消收藏
                    CommunityDataManager.removeFromFavorite(item.id)
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    // 未收藏，添加收藏
                    CommunityDataManager.addToFavorite(item)
                    Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
                }
                updateFavoriteIcon()
            }
        }
        
        // Talk to seller button click listener
        findViewById<TextView>(R.id.btn_talk).setOnClickListener {
            startActivity(Intent(this, Community5Activity::class.java))
        }
    }
    
    private fun updateFavoriteIcon() {
        currentItem?.let { item ->
            if (CommunityDataManager.isFavorite(item.id)) {
                // 已收藏，显示实心图标
                ivFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                // 未收藏，显示空心图标
                ivFavorite.setImageResource(R.drawable.ic_unfavorite)
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        // 每次返回时更新收藏状态
        updateFavoriteIcon()
    }
    
    // ViewPager Adapter
    private class ImagePagerAdapter(
        private val images: List<Any>,
        private val isFilePath: Boolean
    ) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val imageView = ImageView(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
            return ImageViewHolder(imageView)
        }
        
        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            holder.bind(images[position], isFilePath)
        }
        
        override fun getItemCount(): Int = images.size
        
        class ImageViewHolder(private val imageView: ImageView) : 
            RecyclerView.ViewHolder(imageView) {
            fun bind(image: Any, isFilePath: Boolean) {
                if (isFilePath && image is String) {
                    // Load from file path
                    val file = java.io.File(image)
                    if (file.exists()) {
                        try {
                            val bitmap = android.graphics.BitmapFactory.decodeFile(file.absolutePath)
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap)
                            } else {
                                imageView.setImageResource(R.drawable.img_tool_1)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            imageView.setImageResource(R.drawable.img_tool_1)
                        }
                    } else {
                        imageView.setImageResource(R.drawable.img_tool_1)
                    }
                } else if (image is Int) {
                    // Load from resource
                    imageView.setImageResource(image)
                }
            }
        }
    }
}
