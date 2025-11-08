package com.plant.way

data class CareEvent(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val time: String,
    val iconRes: Int
) {
    companion object {
        const val TYPE_WATERING = "Watering"
        const val TYPE_FERTILIZING = "Fertilizing"
        const val TYPE_PRUNING = "Pruning"
        const val TYPE_PEST_CONTROL = "Pest control"
    }
}
