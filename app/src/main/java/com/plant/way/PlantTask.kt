package com.plant.way

data class PlantTask(
    val id: Int,
    val taskType: TaskType,
    val taskDate: String,
    val plantId: Int
)

enum class TaskType {
    WATERING,
    FERTILIZING,
    PRUNING,
    REPOTTING
}
