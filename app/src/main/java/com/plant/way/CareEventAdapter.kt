package com.plant.way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CareEventAdapter(
    private val events: MutableList<CareEvent>
) : RecyclerView.Adapter<CareEventAdapter.CareEventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareEventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_care_event, parent, false)
        return CareEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: CareEventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    fun addEvent(event: CareEvent) {
        events.add(event)
        notifyItemInserted(events.size - 1)
    }

    class CareEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        private val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)

        fun bind(event: CareEvent) {
            tvTitle.text = event.title
            tvTime.text = event.time
            ivIcon.setImageResource(event.iconRes)
        }
    }
}
