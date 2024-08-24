package com.example.prayerbox.models

import java.time.LocalDate

data class Prayer(var name:String, var content:String, var dateCreated: LocalDate) {
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
