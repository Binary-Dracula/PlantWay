package com.plant.way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoriteAdapter(
    private var items: List<CommunityItem>,
    private val onItemClick: (CommunityItem) -> Unit,
    private val onItemLongClick: (CommunityItem) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.iv_product)
        val productName: TextView = itemView.findViewById(R.id.tv_product_name)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val sellerAvatar: ImageView = itemView.findViewById(R.id.iv_seller_avatar)
        val sellerName: TextView = itemView.findViewById(R.id.tv_seller_name)

        fun bind(item: CommunityItem) {
            // 加载图片
            if (!item.imagePath.isNullOrEmpty()) {
                val file = java.io.File(item.imagePath)
                if (file.exists()) {
                    try {
                        val bitmap = android.graphics.BitmapFactory.decodeFile(file.absolutePath)
                        if (bitmap != null) {
                            productImage.setImageBitmap(bitmap)
                        } else {
                            productImage.setImageResource(item.imageResId)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        productImage.setImageResource(item.imageResId)
                    }
                } else {
                    productImage.setImageResource(item.imageResId)
                }
            } else {
                productImage.setImageResource(item.imageResId)
            }
            
            productName.text = item.title
            price.text = item.price
            sellerAvatar.setImageResource(item.sellerAvatar)
            sellerName.text = item.sellerName
            
            itemView.setOnClickListener {
                onItemClick(item)
            }
            
            itemView.setOnLongClickListener {
                onItemLongClick(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<CommunityItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
