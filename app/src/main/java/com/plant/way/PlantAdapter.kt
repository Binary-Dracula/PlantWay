package com.plant.way

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rishabhharit.roundedimageview.RoundedImageView

class PlantAdapter(
    private val plantList: List<PlantItem>,
    private val onItemClick: (PlantItem) -> Unit,
    private val onItemLongClick: ((PlantItem) -> Unit)? = null
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPlantImage: RoundedImageView = itemView.findViewById(R.id.iv_plant_image)
        val tvJoinTime: TextView = itemView.findViewById(R.id.tv_join_time)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val tvPlantName: TextView = itemView.findViewById(R.id.tv_plant_name)
        
        fun bind(plant: PlantItem) {
            // 优先显示用户拍摄的图片，否则显示默认资源图片
            if (plant.imageUri != null) {
                ivPlantImage.setImageURI(Uri.parse(plant.imageUri))
            } else if (plant.imageResId != 0) {
                ivPlantImage.setImageResource(plant.imageResId)
            }
            
            tvJoinTime.text = plant.joinTime
            tvDate.text = plant.date
            tvPlantName.text = plant.name
            
            itemView.setOnClickListener {
                onItemClick(plant)
            }
            
            itemView.setOnLongClickListener {
                onItemLongClick?.invoke(plant)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant_schedule, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int = plantList.size
}
