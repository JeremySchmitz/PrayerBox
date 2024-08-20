package com.example.prayerbox.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

data class Prayer @RequiresApi(Build.VERSION_CODES.O) constructor(var name:String, var content:String, var dateCreated: java.time.LocalDate) {
    var dateAnswered: LocalDate? = null
    var contentAnswered: String = ""
}
