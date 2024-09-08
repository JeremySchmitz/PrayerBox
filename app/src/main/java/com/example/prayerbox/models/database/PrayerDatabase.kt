package com.example.prayerbox.models.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.prayerbox.models.Prayer

@Database(
    entities = [Prayer::class],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ],
    exportSchema = true
)

@TypeConverters(LocalDateConverter::class)
abstract class PrayerDatabase: RoomDatabase() {
    abstract  val dao: PrayerDao
}