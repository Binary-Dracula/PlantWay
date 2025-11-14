package com.plant.way

data class CommunityItem(
    val id: Int,
    val title: String,
    val price: String,
    val description: String,
    val imageResId: Int = R.drawable.community_2_flower,
    val imagePath: String? = null, // 用户拍照的图片路径
    val sellerName: String,
    val sellerAvatar: Int,
    val isUserAdded: Boolean = false
)
