package com.plant.way.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.plant.way.R

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = TextView(requireContext()).apply {
            text = "Start Fragment"
            textSize = 24f
            gravity = android.view.Gravity.CENTER
        }
        return view
    }

    companion object {
        fun newInstance() = StartFragment()
    }
}