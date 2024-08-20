package com.example.prayerbox.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prayerbox.data.CreatePrayerScreenEvents
import com.example.prayerbox.data.CreatePrayerScreenState

class CreatePrayerScreenViewModel:ViewModel() {

    var  uiState = mutableStateOf(CreatePrayerScreenState())

    fun onEvent(event:CreatePrayerScreenEvents){
        when(event){
            is CreatePrayerScreenEvents.TitleUpdated -> {
                uiState.value = uiState.value.copy(
                    title = event.title
                )
            }

            is CreatePrayerScreenEvents.ContentUpdated -> {
                uiState.value = uiState.value.copy(
                    content = event.content
                )
            }

            is CreatePrayerScreenEvents.PrayerCreated -> {
                uiState.value = uiState.value.copy(
                    prayers = uiState.value.prayers.plus(event.prayer)
                )
            }

            is CreatePrayerScreenEvents.PrayerDeleted -> {
                uiState.value = uiState.value.copy(
                    prayers = uiState.value.prayers.minus(event.prayer)
                )
            }
        }
    }
}


