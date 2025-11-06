package com.plant.way

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var selectedPosition = 0
    private var onItemSelectedListener: ((Int) -> Unit)? = null
    
    private val tabItems = mutableListOf<TabItem>()

    init {
        orientation = HORIZONTAL
        setBackgroundResource(R.drawable.navbar_bg)
        elevation = 8f
        setupTabs()
    }

    private fun setupTabs() {
        val tabData = listOf(
            TabData(R.drawable.tab_1_selected, R.drawable.tab_1_unselected, R.string.title_start),
            TabData(R.drawable.tab_2_selected, R.drawable.tab_2_unselected, R.string.title_schedule),
            TabData(R.drawable.tab_3_selected, R.drawable.tab_3_unselected, R.string.title_community),
            TabData(R.drawable.tab_4_selected, R.drawable.tab_4_unselected, R.string.title_profile)
        )

        tabData.forEachIndexed { index, data ->
            val tabItem = createTabItem(data, index)
            tabItems.add(tabItem)
            addView(tabItem.container)
        }
        
        // 默认选中第一个
        updateTabSelection(0)
    }

    private fun createTabItem(data: TabData, position: Int): TabItem {
        val container = LinearLayout(context).apply {
            orientation = VERTICAL
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
            setPadding(8, 12, 8, 12)
            gravity = android.view.Gravity.CENTER
            setOnClickListener {
                setSelectedPosition(position)
            }
        }

        val icon = ImageView(context).apply {
            layoutParams = LayoutParams(48, 48).apply {
                gravity = android.view.Gravity.CENTER_HORIZONTAL
            }
            scaleType = ImageView.ScaleType.FIT_CENTER
        }

        val text = TextView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER_HORIZONTAL
                topMargin = 4
            }
            setText(data.textRes)
            textSize = 11f
            gravity = android.view.Gravity.CENTER
        }

        container.addView(icon)
        container.addView(text)

        return TabItem(container, icon, text, data)
    }

    private fun updateTabSelection(position: Int) {
        tabItems.forEachIndexed { index, tabItem ->
            val isSelected = index == position
            val iconRes = if (isSelected) tabItem.data.selectedIcon else tabItem.data.unselectedIcon
            val textColor = if (isSelected) {
                ContextCompat.getColor(context, android.R.color.white)
            } else {
                ContextCompat.getColor(context, android.R.color.white)
            }
            
            tabItem.icon.setImageResource(iconRes)
            tabItem.text.setTextColor(textColor)
            tabItem.text.alpha = if (isSelected) 1.0f else 0.7f
        }
    }

    fun setSelectedPosition(position: Int) {
        if (position != selectedPosition && position in 0 until tabItems.size) {
            selectedPosition = position
            updateTabSelection(position)
            onItemSelectedListener?.invoke(position)
        }
    }

    fun setOnItemSelectedListener(listener: (Int) -> Unit) {
        onItemSelectedListener = listener
    }

    fun getSelectedPosition(): Int = selectedPosition

    private data class TabData(
        val selectedIcon: Int,
        val unselectedIcon: Int,
        val textRes: Int
    )

    private data class TabItem(
        val container: LinearLayout,
        val icon: ImageView,
        val text: TextView,
        val data: TabData
    )
}