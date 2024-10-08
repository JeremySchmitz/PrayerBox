package com.example.prayerbox.models.database

import androidx.room.TypeConverter
import java.time.LocalDate

public class LocalDateConverter {
    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate?{
        return if (dateString == null) null
        else LocalDate.parse(dateString)
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }
}