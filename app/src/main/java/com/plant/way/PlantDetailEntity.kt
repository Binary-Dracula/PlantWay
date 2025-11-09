package com.plant.way

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PlantDetailEntity(
    val topImageResource: Int,
    val plantName: String,
    val wateringText: String,
    val fertilizingText: String,
    val pruningText: String,
    val pestAndDiseasePreventionText: String,
    val lightAndTemperatureText: String,
    val soilRequirementsText: String
) : Parcelable