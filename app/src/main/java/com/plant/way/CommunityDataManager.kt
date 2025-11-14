package com.plant.way

object CommunityDataManager {
    private val communityList = mutableListOf<CommunityItem>()
    private val favoriteList = mutableListOf<CommunityItem>()
    private var nextId = 1
    
    // 数据变化监听器
    private val listeners = mutableListOf<() -> Unit>()
    private val favoriteListeners = mutableListOf<() -> Unit>()
    
    init {
        // 初始化默认数据
        communityList.add(
            CommunityItem(
                id = nextId++,
                title = "Gardening Kit",
                price = "28",
                description = """
                    Gardening kit, made of stainless steel with orange wooden handles. The set includes a small shovel, hoe, and rake; all are exquisitely crafted and comfortable to hold.
                    Suitable for home gardening. The shovel and hoe feature a curved design for easy handling. The handles are sturdy and durable, featuring the brand logo.
                """.trimIndent(),
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
                description = """
                    Brand new large garden shears, originally sold for over 50, now on clearance sale! Grab them while you can! Size: Small, non-extendable: Total length 57cm, blade length 27cm, weight approximately 0.9kg

                    The listed price is the selling price. Contact me if interested.
                """.trimIndent(),
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
                description = """
                    In stock. Local pickup available.
                    Brought back from the wild; I've raised it myself for two years.
                """.trimIndent(),
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
                description = """
                    Shipped with flower buds, only 16 yuan.
                    Grown on my own balcony; as long as there's good ventilation, even an enclosed balcony can grow it.
                    Blooms year-round in suitable temperatures, producing a huge quantity of flowers with an intoxicating fragrance.
                """.trimIndent(),
                imageResId = R.drawable.img_tool_3,
                sellerName = "Olivia",
                sellerAvatar = R.drawable.avatar,
                isUserAdded = true
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
    
    // 收藏功能
    fun getFavoriteList(): List<CommunityItem> = favoriteList.toList()
    
    fun addToFavorite(item: CommunityItem) {
        if (!favoriteList.any { it.id == item.id }) {
            favoriteList.add(item)
            notifyFavoriteChanged()
        }
    }
    
    fun removeFromFavorite(id: Int) {
        favoriteList.removeAll { it.id == id }
        notifyFavoriteChanged()
    }
    
    fun isFavorite(id: Int): Boolean {
        return favoriteList.any { it.id == id }
    }
    
    fun addDataChangeListener(listener: () -> Unit) {
        listeners.add(listener)
    }
    
    fun removeDataChangeListener(listener: () -> Unit) {
        listeners.remove(listener)
    }
    
    fun addFavoriteChangeListener(listener: () -> Unit) {
        favoriteListeners.add(listener)
    }
    
    fun removeFavoriteChangeListener(listener: () -> Unit) {
        favoriteListeners.remove(listener)
    }
    
    private fun notifyDataChanged() {
        listeners.forEach { it.invoke() }
    }
    
    private fun notifyFavoriteChanged() {
        favoriteListeners.forEach { it.invoke() }
    }
}
