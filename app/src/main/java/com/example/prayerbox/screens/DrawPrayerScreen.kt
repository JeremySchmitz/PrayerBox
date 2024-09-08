package com.example.prayerbox.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prayerbox.models.DrawPrayerScreenViewModel
import com.example.prayerbox.widgets.DateInput
import com.example.prayerbox.widgets.InputDialog
import com.example.prayerbox.widgets.PrayerCard
import com.example.prayerbox.widgets.TextInput
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawPrayerScreen(viewModel: DrawPrayerScreenViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var showAnswerDialog by remember { mutableStateOf(false) }
        var currentPrayer: Int? by remember { mutableStateOf(null) }
        val coroutineScope = rememberCoroutineScope()
        val datePickerState = rememberDatePickerState()

        if (showAnswerDialog && currentPrayer != null) {
            var answeredContent by remember { mutableStateOf("") }
            InputDialog(
                modifier = Modifier.height(450.dp),
                dialogTitle = "Answered Details",
                onDismissRequest = { showAnswerDialog = false }
            ) {
                TextInput(
                    modifier = Modifier.height(180.dp),
                    title = "Details",
                    value = answeredContent,
                    onTextChange = { answeredContent = it })

                DateInput(title = "Date Answered", datePickerState = datePickerState)

                Spacer(modifier = Modifier.fillMaxHeight())

                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = {
                        datePickerState.selectedDateMillis = null
                        showAnswerDialog = false
                    }) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        enabled = datePickerState.selectedDateMillis != null,
                        onClick = {
                            viewModel.answerPrayer(
                                currentPrayer!!,
                                answeredContent,
                                datePickerState.selectedDateMillis!!
                            )
                            showAnswerDialog = false
                            datePickerState.selectedDateMillis = null
                        },
                    ) {
                        Text(text = "Done")
                    }
                }

            }
        }

        Column {
            Box(modifier = Modifier.weight(1f)) {
                if (viewModel.drawnPrayers.isNotEmpty()) {
                    Box(modifier = Modifier.height(300.dp)) {
                        LazyRow(state = viewModel.scrollState) {
                            itemsIndexed(viewModel.drawnPrayers) { index, item ->
                                Box(modifier = Modifier.padding(8.dp)) {
                                    PrayerCard(prayer = item, onAnswered = {
                                        currentPrayer = index
                                        showAnswerDialog = true
//                                        viewModel.answerPrayer(it, "content")
                                    })
                                }
                            }
                        }
                    }
                }
            }

            Row {
                Button(onClick = {
                    coroutineScope.launch {
                        viewModel.drawPrayer()
                    }
                }, enabled = !viewModel.drawableAreEmpty) {
                    Text(text = "Draw Prayer")
                }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = {
                        viewModel.clearDrawnPrayers()
                    }, enabled = viewModel.drawnPrayers.isNotEmpty() && !viewModel.drawableAreEmpty) {
                        Text(text = "Clear")

                    }

                if (viewModel.drawableAreEmpty) {
                    Spacer(modifier = Modifier.weight(1f))

                    //BUG Have to click twice to activate
                    Button(onClick = {
                        coroutineScope.launch {
                            viewModel.reloadUnansweredPrayers()
                        }
                    }) {
                        Text(text = "Reload")
                    }
                }
            }

        }

    }

}

@Preview
@Composable
fun DrawPrayerScreenPreview() {
    DrawPrayerScreen(viewModel())
}