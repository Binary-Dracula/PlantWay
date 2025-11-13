package com.plant.way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlantTaskListAdapter(
    private val tasks: MutableList<PlantTask>,
    private val onTaskLongClick: ((PlantTask) -> Unit)? = null
) : RecyclerView.Adapter<PlantTaskListAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_care_event, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], onTaskLongClick)
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: PlantTask) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun removeTask(task: PlantTask) {
        val position = tasks.indexOf(task)
        if (position != -1) {
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        private val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)

        fun bind(task: PlantTask, onTaskLongClick: ((PlantTask) -> Unit)?) {
            tvTime.text = task.taskDate
            
            // 根据任务类型设置标题和图标
            when (task.taskType) {
                TaskType.WATERING -> {
                    tvTitle.text = "Watering"
                    ivIcon.setImageResource(R.drawable.ic_schedule_7_1)
                }
                TaskType.FERTILIZING -> {
                    tvTitle.text = "Fertilizing"
                    ivIcon.setImageResource(R.drawable.ic_schedule_7_2)
                }
                TaskType.PRUNING -> {
                    tvTitle.text = "Pruning"
                    ivIcon.setImageResource(R.drawable.ic_schedule_7_3)
                }
                TaskType.REPOTTING -> {
                    tvTitle.text = "Repotting"
                    ivIcon.setImageResource(R.drawable.ic_schedule_7_4)
                }
            }
            
            // 设置长按监听器
            itemView.setOnLongClickListener {
                onTaskLongClick?.invoke(task)
                true
            }
        }
    }
}
