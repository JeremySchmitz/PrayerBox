package com.example.prayerbox.data

import com.example.prayerbox.models.Prayer

data class CreatePrayerScreenState(
    var title: String = "",
    var content: String = "",
    var prayers: List<Prayer> = mutableListOf()
)
