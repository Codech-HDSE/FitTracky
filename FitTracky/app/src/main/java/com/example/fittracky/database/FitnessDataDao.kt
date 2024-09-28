package com.example.fittracky.database

import androidx.room.*
import com.example.fittracky.model.FitnessData

@Dao
interface FitnessDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitnessData(fitnessData: FitnessData)

    @Query("SELECT * FROM fitness_data WHERE date = :date")
    suspend fun getFitnessDataByDate(date: String): FitnessData?

    @Query("SELECT * FROM fitness_data ORDER BY date DESC")
    suspend fun getAllFitnessData(): List<FitnessData>
}
