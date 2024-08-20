package com.example.prayerbox.data

import com.example.prayerbox.models.Prayer

sealed class CreatePrayerScreenEvents{
    data class TitleUpdated(val title: String):CreatePrayerScreenEvents()
    data class ContentUpdated(val content: String):CreatePrayerScreenEvents()
    data class PrayerCreated(val prayer: Prayer):CreatePrayerScreenEvents()
    data class PrayerDeleted(val prayer: Prayer):CreatePrayerScreenEvents()
}