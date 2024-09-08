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
import java.time.LocalDate

class DrawPrayerScreenViewModel(private val dao: PrayerDao) : ViewModel() {

    val scrollState = LazyListState()

    private val _unansweredPrayers =
        dao.getUnansweredPrayers().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
     //TODO Update so drawn prayers cannot be drawn again
//    private var _availablePrayers: MutableStateFlow<List<Prayer>> = MutableStateFlow(emptyList<Prayer>())
//    private val _availablePrayers: SnapshotStateList<Prayer> = dummyPrayers().toMutableStateList()

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
        if (_unansweredPrayers.value.isEmpty())
            return

        val drawn = _unansweredPrayers.value.random()
        _drawnPrayers.add(drawn)
//        _availablePrayers.remove(drawn)
        scrollToEnd()


    }

    private suspend fun scrollToEnd() {
        scrollState.animateScrollToItem(_drawnPrayers.lastIndex)
    }

}

fun dummyPrayers(): List<Prayer> {
    return listOf(
        Prayer(
            "Title 1",
            "Content 1",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 2",
            "Content 2",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 3",
            "Content 3",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 4",
            "Content 4",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 5",
            "Content 5",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 6",
            "Content 6",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 7",
            "Content 7",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 8",
            "Content 8",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 9",
            "Content 9",
            LocalDate.parse("2018-02-03")
        ),
        Prayer(
            "Title 10",
            "Content 10",
            LocalDate.parse("2018-02-03")
        ),
    )
}