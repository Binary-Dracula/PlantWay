package com.plant.way.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.plant.way.R
import com.plant.way.Schedule3Activity

class ScheduleFragment : Fragment() {

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
    }

    private fun setupClickListeners(view: View) {
        // Add plant button click listener
        view.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            startActivity(Intent(requireActivity(), Schedule3Activity::class.java))
        }
    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }
}