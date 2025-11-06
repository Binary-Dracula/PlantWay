package com.plant.way.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.plant.way.R

class CommunityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners(view)
    }

    private fun setupClickListeners(view: View) {
        // Add button click listener
        view.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            Toast.makeText(requireContext(), "Add new item", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = CommunityFragment()
    }
}