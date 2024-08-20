package com.example.prayerbox.data

import com.example.prayerbox.models.Prayer

data class CreatePrayerScreenState(
    var prayers: List<Prayer> = mutableListOf()
)
