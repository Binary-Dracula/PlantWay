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
    private var likeCount = 1
    private var favoriteCount = 1
    private var isLiked = false
    private var isFavorited = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community3)
        
        setupViews()
    }
    
    private fun setupViews() {
        vpImages = findViewById(R.id.vp_images)
        tvLikeCount = findViewById(R.id.tv_like_count)
        tvFavoriteCount = findViewById(R.id.tv_favorite_count)
        
        // Setup ViewPager with images
        val images = listOf(R.drawable.img_tool_1, R.drawable.img_tool_2)
        vpImages.adapter = ImagePagerAdapter(images)
        
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
        
        // Favorite button click listener
        findViewById<LinearLayout>(R.id.ll_favorite).setOnClickListener {
            isFavorited = !isFavorited
            if (isFavorited) {
                favoriteCount++
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
            } else {
                favoriteCount--
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
            }
            tvFavoriteCount.text = favoriteCount.toString()
        }
        
        // Talk to seller button click listener
        findViewById<TextView>(R.id.btn_talk).setOnClickListener {
            startActivity(Intent(this, Community5Activity::class.java))
        }
    }
    
    // ViewPager Adapter
    private class ImagePagerAdapter(private val images: List<Int>) : 
        RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {
        
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
            holder.bind(images[position])
        }
        
        override fun getItemCount(): Int = images.size
        
        class ImageViewHolder(private val imageView: ImageView) : 
            RecyclerView.ViewHolder(imageView) {
            fun bind(imageRes: Int) {
                imageView.setImageResource(imageRes)
            }
        }
    }
}
