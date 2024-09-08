package com.example.prayerbox.models.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.prayerbox.models.Prayer
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerDao {
    @Insert
    suspend fun insertPrayer(prayer: Prayer)

    @Upsert
    suspend fun upsertPrayer(prayer: Prayer)

    @Delete
    suspend fun  deletePrayer(prayer: Prayer)

    @Query("SELECT * FROM prayer")
    fun getPrayers(): Flow<List<Prayer>>

    @Query("SELECT * FROM prayer WHERE dateAnswered IS NOT null")
    fun getAnsweredPrayers(): Flow<List<Prayer>>

    @Query("SELECT * FROM prayer WHERE dateAnswered IS null")
    fun getUnansweredPrayers(): Flow<List<Prayer>>
}