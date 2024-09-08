package com.example.prayerbox.models

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prayerbox.models.database.PrayerDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class DrawPrayerScreenViewModel(private val dao: PrayerDao) : ViewModel() {

    val scrollState = LazyListState()

    private val _drawablePrayers =
        dao.getDrawablePrayers().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private var _drawnPrayers: SnapshotStateList<Prayer> = mutableStateListOf()
    val drawnPrayers
        get() = _drawnPrayers

    private val _answeredPrayers: SnapshotStateList<Prayer> = mutableStateListOf()

    fun answerPrayer(prayer: Prayer, answer: String, date: Long) {
        val i = _drawnPrayers.indexOf(prayer)
        val updatedPrayer = prayer.copy()

        println("Date ${Utils.convertMillisToDate(date)}")
        updatedPrayer.dateAnswered = LocalDate.parse(Utils.convertMillisToDate(date, "yyyy-MM-dd"))
        updatedPrayer.contentAnswered = answer

        val tempList = _drawnPrayers.toMutableStateList()
        tempList[i] = updatedPrayer
        _answeredPrayers.add(updatedPrayer)
        _drawnPrayers.remove(prayer)
        _drawnPrayers.add(i, updatedPrayer)
    }

    suspend fun drawPrayer() {
        if (_drawablePrayers.value.isEmpty())
            return

        val drawn = _drawablePrayers.value.random()
        _drawnPrayers.add(drawn)

        drawn.drawn = true;
        viewModelScope.launch {
            dao.upsertPrayer(drawn)
        }
        scrollToEnd()
    }

    private suspend fun scrollToEnd() {
        scrollState.animateScrollToItem(_drawnPrayers.lastIndex)
    }

}