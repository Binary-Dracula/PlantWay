package com.plant.way

object CommunityDataManager {
    private val communityList = mutableListOf<CommunityItem>()
    private var nextId = 1
    
    // 数据变化监听器
    private val listeners = mutableListOf<() -> Unit>()
    
    init {
        // 初始化默认数据
        communityList.add(
            CommunityItem(
                id = nextId++,
                title = "Gardening Kit",
                price = "28",
                description = "Complete gardening kit",
                imageResId = R.drawable.img_tool_2,
                sellerName = "SASA",
                sellerAvatar = R.drawable.avatar_sasa,
                isUserAdded = false
            )
        )
        communityList.add(
            CommunityItem(
                id = nextId++,
                title = "Garden Shears",
                price = "9.9",
                description = "Professional garden shears",
                imageResId = R.drawable.img_tool_4,
                sellerName = "SASA",
                sellerAvatar = R.drawable.avatar_sasa,
                isUserAdded = false
            )
        )
        communityList.add(
            CommunityItem(
                id = nextId++,
                title = "Dendrobium",
                price = "30",
                description = "Beautiful dendrobium plant",
                imageResId = R.drawable.img_tool_5,
                sellerName = "SASA",
                sellerAvatar = R.drawable.avatar_sasa,
                isUserAdded = false
            )
        )
        communityList.add(
            CommunityItem(
                id = nextId++,
                title = "China Rose",
                price = "16",
                description = "Lovely china rose",
                imageResId = R.drawable.img_tool_3,
                sellerName = "Olivia",
                sellerAvatar = R.drawable.avatar,
                isUserAdded = false
            )
        )
    }
    
    fun getCommunityList(): List<CommunityItem> = communityList.toList()
    
    fun getUserAddedItems(): List<CommunityItem> = communityList.filter { it.isUserAdded }
    
    fun addItem(item: CommunityItem) {
        val newItem = item.copy(id = nextId++, isUserAdded = true)
        communityList.add(newItem)
        notifyDataChanged()
    }
    
    fun removeItem(id: Int) {
        communityList.removeAll { it.id == id }
        notifyDataChanged()
    }
    
    fun addDataChangeListener(listener: () -> Unit) {
        listeners.add(listener)
    }
    
    fun removeDataChangeListener(listener: () -> Unit) {
        listeners.remove(listener)
    }
    
    private fun notifyDataChanged() {
        listeners.forEach { it.invoke() }
    }
}
