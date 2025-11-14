package com.plant.way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileCommunityAdapter(
    private var items: List<CommunityItem>,
    private val onItemClick: (CommunityItem) -> Unit,
    private val onItemLongClick: (CommunityItem) -> Unit
) : RecyclerView.Adapter<ProfileCommunityAdapter.ProfileCommunityViewHolder>() {

    inner class ProfileCommunityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.iv_item_image)
        val itemTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val itemPrice: TextView = itemView.findViewById(R.id.tv_item_price)
        val sellerAvatar: ImageView = itemView.findViewById(R.id.iv_seller_avatar)
        val sellerName: TextView = itemView.findViewById(R.id.tv_seller_name)

        fun bind(item: CommunityItem) {
            // 如果有用户拍摄的图片，显示图片路径，否则显示默认资源
            if (!item.imagePath.isNullOrEmpty()) {
                val file = java.io.File(item.imagePath)
                if (file.exists()) {
                    try {
                        val bitmap = android.graphics.BitmapFactory.decodeFile(file.absolutePath)
                        if (bitmap != null) {
                            itemImage.setImageBitmap(bitmap)
                        } else {
                            itemImage.setImageResource(item.imageResId)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        itemImage.setImageResource(item.imageResId)
                    }
                } else {
                    itemImage.setImageResource(item.imageResId)
                }
            } else {
                itemImage.setImageResource(item.imageResId)
            }
            
            itemTitle.text = item.title
            itemPrice.text = item.price
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCommunityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_community, parent, false)
        return ProfileCommunityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileCommunityViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<CommunityItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
