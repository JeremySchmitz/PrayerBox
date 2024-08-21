package com.example.prayerbox.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class CreatePrayerScreenViewModel : ViewModel() {

    var title by mutableStateOf("")

    var content by mutableStateOf("")


    private val _prayers: SnapshotStateList<Prayer> = mutableStateListOf()

    val prayers
        get() = _prayers

    fun remove(prayer: Prayer) {
        _prayers.remove(prayer)
    }

    fun add(prayer: Prayer) {
        _prayers.add(prayer)
        title = ""
        content = ""
    }

    fun addEnabled(): Boolean {
        /* TODO
        *  Dont allow two prayers with same title
        * */
        return title.isNotEmpty() && content.isNotEmpty()
    }

}


