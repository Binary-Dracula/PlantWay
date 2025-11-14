package com.plant.way.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plant.way.Community2Activity
import com.plant.way.Community3Activity
import com.plant.way.CommunityAdapter
import com.plant.way.CommunityDataManager
import com.plant.way.R

class CommunityFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CommunityAdapter
    private val dataChangeListener: () -> Unit = {
        updateData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView(view)
        setupClickListeners(view)
        
        // 注册数据变化监听
        CommunityDataManager.addDataChangeListener(dataChangeListener)
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_community)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        
        adapter = CommunityAdapter(CommunityDataManager.getCommunityList()) { item ->
            // 点击item跳转到详情页
            startActivity(Intent(requireActivity(), Community3Activity::class.java))
        }
        recyclerView.adapter = adapter
    }

    private fun setupClickListeners(view: View) {
        // Add button click listener
        view.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            startActivity(Intent(requireActivity(), Community2Activity::class.java))
        }
    }
    
    private fun updateData() {
        adapter.updateData(CommunityDataManager.getCommunityList())
    }
    
    override fun onResume() {
        super.onResume()
        updateData()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        CommunityDataManager.removeDataChangeListener(dataChangeListener)
    }

    companion object {
        fun newInstance() = CommunityFragment()
    }
}