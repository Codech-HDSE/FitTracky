package com.example.fittracky.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fitness_data")
data class FitnessData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null, // Set default to null
    val date: String?,
    val steps: Int,
    val distance: Double,
    val calories: Double
)



