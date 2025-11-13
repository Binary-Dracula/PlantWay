package com.plant.way

data class PlantItem(
    val id: Int,
    val name: String,
    val joinTime: String,
    val date: String,
    val imageResId: Int = 0,
    val imageUri: String? = null,  // 用户拍照或选择的图片URI
    val tasks: MutableList<PlantTask> = mutableListOf()
)
