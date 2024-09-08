package com.example.prayerbox.models

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prayerbox.models.database.PrayerDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class DrawPrayerScreenViewModel(private val dao: PrayerDao) : ViewModel() {
    private val _drawablePrayers =
        dao.getDrawablePrayers()
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _drawnPrayers: SnapshotStateList<Prayer> = mutableStateListOf()
    val drawnPrayers
        get() = _drawnPrayers

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _drawableAreEmpty = dao.getDrawablePrayers().mapLatest { it.isEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val drawableAreEmpty
        get() = _drawableAreEmpty.value

    val scrollState = LazyListState()

    fun answerPrayer(index: Int, answer: String, date: Long) {
        val updatedPrayer = _drawnPrayers[index]

        println("Date ${Utils.convertMillisToDate(date)}")
        updatedPrayer.dateAnswered = LocalDate.parse(Utils.convertMillisToDate(date, "yyyy-MM-dd"))
        updatedPrayer.contentAnswered = answer

        _drawnPrayers.removeAt(index)
        _drawnPrayers.add(index, updatedPrayer)

        viewModelScope.launch { dao.upsertPrayer(updatedPrayer) }
    }

    suspend fun drawPrayer() {
        if (drawableAreEmpty) return

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

    fun clearDrawnPrayers(){
        _drawnPrayers.clear()
    }

    suspend fun reloadUnansweredPrayers() {
        clearDrawnPrayers()
        viewModelScope.launch {
            dao.reloadUnansweredPrayers()
        }
    }
}