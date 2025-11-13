package com.plant.way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rishabhharit.roundedimageview.RoundedImageView

class PlantTaskAdapter(
    private val taskList: List<Pair<PlantItem, PlantTask>>,
    private val onItemClick: (PlantItem, PlantTask) -> Unit
) : RecyclerView.Adapter<PlantTaskAdapter.PlantTaskViewHolder>() {

    inner class PlantTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPlantImage: RoundedImageView = itemView.findViewById(R.id.iv_task_plant_image)
        val ivTaskIcon: ImageView = itemView.findViewById(R.id.iv_task_icon)
        val tvTaskType: TextView = itemView.findViewById(R.id.tv_task_type)
        val tvTaskDate: TextView = itemView.findViewById(R.id.tv_task_date)
        val tvPlantName: TextView = itemView.findViewById(R.id.tv_task_plant_name)
        
        fun bind(plant: PlantItem, task: PlantTask) {
            // 优先显示用户拍摄的图片，否则显示默认资源图片
            if (plant.imageUri != null) {
                ivPlantImage.setImageURI(android.net.Uri.parse(plant.imageUri))
            } else if (plant.imageResId != 0) {
                ivPlantImage.setImageResource(plant.imageResId)
            }
            
            tvPlantName.text = plant.name
            tvTaskDate.text = task.taskDate
            
            // 根据任务类型显示不同的图标和文本
            when (task.taskType) {
                TaskType.WATERING -> {
                    ivTaskIcon.setImageResource(R.drawable.ic_tool_1)
                    tvTaskType.text = itemView.context.getString(R.string.schedule_watering)
                }
                TaskType.FERTILIZING -> {
                    ivTaskIcon.setImageResource(R.drawable.ic_tool_2)
                    tvTaskType.text = itemView.context.getString(R.string.schedule_fertilizing)
                }
                TaskType.PRUNING -> {
                    ivTaskIcon.setImageResource(R.drawable.ic_schedule_7_3)
                    tvTaskType.text = "Pruning"
                }
                TaskType.REPOTTING -> {
                    ivTaskIcon.setImageResource(R.drawable.ic_schedule_7_4)
                    tvTaskType.text = "Repotting"
                }
            }
            
            itemView.setOnClickListener {
                onItemClick(plant, task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantTaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant_task, parent, false)
        return PlantTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantTaskViewHolder, position: Int) {
        val (plant, task) = taskList[position]
        holder.bind(plant, task)
    }

    override fun getItemCount(): Int = taskList.size
}
