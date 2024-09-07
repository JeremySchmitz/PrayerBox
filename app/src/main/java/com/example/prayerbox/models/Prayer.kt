package com.example.prayerbox.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.prayerbox.models.database.LocalDateConverter
import java.time.LocalDate

@Entity
data class Prayer(
    val name:String,
    val content:String,
    val dateCreated: LocalDate,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) {
    var dateAnswered: LocalDate? = null
    var contentAnswered: String = ""

    override fun toString(): String {
        var string = "Name: $name, Content: $content, Date Created: $dateCreated"
        if(dateAnswered != null){
            string+= " Date Answered: $dateAnswered"
            if(contentAnswered.isNotEmpty()) string += " Content Answered: $contentAnswered"
        }
        return string
    }
}
