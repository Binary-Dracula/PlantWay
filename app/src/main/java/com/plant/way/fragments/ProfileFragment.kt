package com.plant.way.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.plant.way.Profile2Activity
import com.plant.way.Profile3Activity
import com.plant.way.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners(view)
    }

    private fun setupClickListeners(view: View) {
        // Action buttons
        view.findViewById<LinearLayout>(R.id.ll_favorites).setOnClickListener {
            startActivity(Intent(requireActivity(), Profile2Activity::class.java))
        }

        view.findViewById<LinearLayout>(R.id.ll_sale).setOnClickListener {
            startActivity(Intent(requireActivity(), Profile3Activity::class.java))
        }

        view.findViewById<LinearLayout>(R.id.ll_message).setOnClickListener {
            Toast.makeText(requireContext(), "Message clicked", Toast.LENGTH_SHORT).show()
        }

        // Settings options
        view.findViewById<LinearLayout>(R.id.ll_change_name).setOnClickListener {
            Toast.makeText(requireContext(), "Change name clicked", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<LinearLayout>(R.id.ll_privacy).setOnClickListener {
            Toast.makeText(requireContext(), "Privacy and Security clicked", Toast.LENGTH_SHORT)
                .show()
        }

        view.findViewById<LinearLayout>(R.id.ll_notifications).setOnClickListener {
            Toast.makeText(requireContext(), "Notifications clicked", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<LinearLayout>(R.id.ll_logout).setOnClickListener {
            Toast.makeText(requireContext(), "Logout clicked", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<LinearLayout>(R.id.ll_delete_account).setOnClickListener {
            Toast.makeText(requireContext(), "Delete Account clicked", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}