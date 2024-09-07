package com.example.prayerbox.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.prayerbox.models.Prayer

@Database(
    entities = [Prayer::class],
    version = 1
)

@TypeConverters(LocalDateConverter::class)
abstract class PrayerDatabase: RoomDatabase() {
    abstract  val dao: PrayerDao
}