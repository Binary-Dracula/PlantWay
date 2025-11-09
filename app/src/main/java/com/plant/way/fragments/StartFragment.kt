package com.plant.way.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.plant.way.R
import com.plant.way.Start21Activity

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners(view)
    }

    private fun setupClickListeners(view: View) {
        // Card 1 click listener
        view.findViewById<LinearLayout>(R.id.ll_card_1).setOnClickListener {
            startActivity(Intent(requireContext(), Start21Activity::class.java))
        }
        
        // Card 2 click listener
        view.findViewById<LinearLayout>(R.id.ll_card_2).setOnClickListener {
            Toast.makeText(requireContext(), "How to choose the right tools?", Toast.LENGTH_SHORT).show()
        }
        
        // Card 3 click listener
        view.findViewById<LinearLayout>(R.id.ll_card_3).setOnClickListener {
            Toast.makeText(requireContext(), "How to plan your balcony space?", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = StartFragment()
    }
}