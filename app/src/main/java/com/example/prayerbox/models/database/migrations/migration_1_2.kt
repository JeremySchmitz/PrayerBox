package com.example.prayerbox.models.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// NOTE: Leaving this here since Im not sure the Auto migration is set up
val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE 'prayer' ADD COLUMN 'drawn' INTEGER NOT NULL DEFAULT FALSE")
    }
}