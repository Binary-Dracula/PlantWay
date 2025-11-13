package com.plant.way

object PlantDataManager {
    private val plantList = mutableListOf<PlantItem>()
    private var nextTaskId = 1
    
    init {
        // 初始化默认数据
        val lemon = PlantItem(
            id = 1,
            name = "Lemon",
            joinTime = "Join time",
            date = "2023 12 01",
            imageResId = R.drawable.ic_flower_2
        )
        lemon.tasks.add(
            PlantTask(
                id = nextTaskId++,
                taskType = TaskType.WATERING,
                taskDate = "2023 12 22",
                plantId = lemon.id
            )
        )
        plantList.add(lemon)
        
        val basil = PlantItem(
            id = 2,
            name = "Basil",
            joinTime = "Join time",
            date = "2023 12 01",
            imageResId = R.drawable.ic_flower_1
        )
        basil.tasks.add(
            PlantTask(
                id = nextTaskId++,
                taskType = TaskType.FERTILIZING,
                taskDate = "2023 12 23",
                plantId = basil.id
            )
        )
        plantList.add(basil)
    }
    
    fun getPlantList(): List<PlantItem> = plantList.toList()
    
    fun getAllTasks(): List<Pair<PlantItem, PlantTask>> {
        val allTasks = mutableListOf<Pair<PlantItem, PlantTask>>()
        plantList.forEach { plant ->
            plant.tasks.forEach { task ->
                allTasks.add(Pair(plant, task))
            }
        }
        return allTasks
    }
    
    fun addPlant(plant: PlantItem) {
        plantList.add(plant)
    }
    
    fun removePlant(id: Int) {
        plantList.removeAll { it.id == id }
    }
    
    fun updatePlant(plant: PlantItem) {
        val index = plantList.indexOfFirst { it.id == plant.id }
        if (index != -1) {
            plantList[index] = plant
        }
    }
    
    fun addTaskToPlant(plantId: Int, taskType: TaskType, taskDate: String) {
        val plant = plantList.find { it.id == plantId }
        plant?.tasks?.add(
            PlantTask(
                id = nextTaskId++,
                taskType = taskType,
                taskDate = taskDate,
                plantId = plantId
            )
        )
    }
    
    fun removeTask(plantId: Int, taskId: Int) {
        val plant = plantList.find { it.id == plantId }
        plant?.tasks?.removeAll { it.id == taskId }
    }
}
