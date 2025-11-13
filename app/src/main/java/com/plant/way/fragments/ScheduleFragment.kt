package com.plant.way.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plant.way.PlantDataManager
import com.plant.way.PlantTaskAdapter
import com.plant.way.R
import com.plant.way.Schedule7Activity
import com.plant.way.Schedule9Activity

class ScheduleFragment : Fragment() {

    private lateinit var plantTaskAdapter: PlantTaskAdapter
    private lateinit var rvPlantTasks: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners(view)
        setupRecyclerView(view)
    }

    override fun onResume() {
        super.onResume()
        // 每次返回时刷新数据
        refreshPlantTasks()
    }

    private fun setupClickListeners(view: View) {
        // Add plant button click listener
        view.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            startActivity(Intent(requireActivity(), Schedule9Activity::class.java))
        }
    }

    private fun setupRecyclerView(view: View) {
        rvPlantTasks = view.findViewById(R.id.rv_plant_tasks)
        rvPlantTasks.layoutManager = LinearLayoutManager(requireContext())
        
        // 从单例类获取所有任务
        val allTasks = PlantDataManager.getAllTasks()
        
        // 设置适配器
        plantTaskAdapter = PlantTaskAdapter(allTasks) { plant, task ->
            // 点击事件处理 - 跳转到Schedule7Activity并传递植物ID
            val intent = Intent(requireActivity(), Schedule7Activity::class.java)
            intent.putExtra(Schedule7Activity.EXTRA_PLANT_ID, plant.id)
            startActivity(intent)
        }
        
        rvPlantTasks.adapter = plantTaskAdapter
    }

    private fun refreshPlantTasks() {
        // 刷新数据
        val allTasks = PlantDataManager.getAllTasks()
        plantTaskAdapter = PlantTaskAdapter(allTasks) { plant, task ->
            // 点击事件处理 - 跳转到Schedule7Activity并传递植物ID
            val intent = Intent(requireActivity(), Schedule7Activity::class.java)
            intent.putExtra(Schedule7Activity.EXTRA_PLANT_ID, plant.id)
            startActivity(intent)
        }
        rvPlantTasks.adapter = plantTaskAdapter
    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }
}