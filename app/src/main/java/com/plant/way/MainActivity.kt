package com.plant.way

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.plant.way.fragments.StartFragment
import com.plant.way.fragments.ScheduleFragment
import com.plant.way.fragments.CommunityFragment
import com.plant.way.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    
    private lateinit var bottomNavigation: CustomBottomNavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupBottomNavigation()
        
        // 默认显示第一个Fragment
        if (savedInstanceState == null) {
            switchFragment(StartFragment.newInstance())
        }
    }
    
    private fun setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
        
        bottomNavigation.setOnItemSelectedListener { position ->
            when (position) {
                0 -> switchFragment(StartFragment.newInstance())
                1 -> switchFragment(ScheduleFragment.newInstance())
                2 -> switchFragment(CommunityFragment.newInstance())
                3 -> switchFragment(ProfileFragment.newInstance())
            }
        }
    }
    
    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}