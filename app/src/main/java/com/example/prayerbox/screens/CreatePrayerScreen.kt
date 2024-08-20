package com.example.prayerbox.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prayerbox.data.CreatePrayerScreenEvents
import com.example.prayerbox.data.CreatePrayerScreenState
import com.example.prayerbox.models.CreatePrayerScreenViewModel
import com.example.prayerbox.models.Prayer
import com.example.prayerbox.widgets.PrayerCard
import com.example.prayerbox.widgets.TextInput
import java.time.LocalDate

@Composable
fun CreatePrayerScreen(createPrayerViewModel: CreatePrayerScreenViewModel) {
    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column {
            Text(text = Routes.CREATE_PRAYER_SCREEN)

            Spacer(modifier = Modifier.size(16.dp))

            TextInput(
                title = "Prayer Title", singleLine = true, onTextChange = {
                    title.value = it
                    createPrayerViewModel.onEvent(
                        CreatePrayerScreenEvents.TitleUpdated(it)
                    )
                }, imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(16.dp))

            TextInput(
                title = "Prayer Content", height = 180.dp, onTextChange = {
                content.value = it
                createPrayerViewModel.onEvent(
                    CreatePrayerScreenEvents.ContentUpdated(it)
                )
            })

            Spacer(modifier = Modifier.size(8.dp))

            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.weight(1F))

                Button(enabled = addEnabled(createPrayerViewModel), onClick = {
                    val newPrayer =  Prayer(title.value, content.value, LocalDate.parse("2018-02-03"))
                    createPrayerViewModel.onEvent(
                        CreatePrayerScreenEvents.PrayerCreated(newPrayer)
                    )
//                    prayers.add(newPrayer)
//                    title.value = ""
//                    content.value = ""
                    println(" ------ add button")
                    println(" ------ add button " + createPrayerViewModel.uiState.value.prayers.size)
                }) {
                    Text(text = "Add Prayer")
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }


            Spacer(modifier = Modifier.size(16.dp))

            Box(modifier = Modifier.height(300.dp)) {
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    for (prayer in createPrayerViewModel.uiState.value.prayers) {
                        Box(modifier = Modifier.padding(8.dp)) {
                            PrayerCard(prayer = prayer, canDelete = true, onDelete = {
                                createPrayerViewModel.onEvent(
                                    CreatePrayerScreenEvents.PrayerDeleted(it)
                                )
//                                prayers.remove(it)
                                println(" ------ remove button")
                                println(" ------ remove button " + createPrayerViewModel.uiState.value.prayers.size)
                            })
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.weight(1F))

                Button(onClick = {
                    println(" ------ submit button")
                    println(" ------ submit button " + createPrayerViewModel.uiState.value.prayers.size)
                }) {
                    Text(text = "Submit", fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.Done, contentDescription = "Submit")
                }
            }
        }


    }
}

@Preview
@Composable
fun CreatePrayerScreenPreview() {
    CreatePrayerScreen(createPrayerViewModel = viewModel())
}


fun addEnabled(createPrayerViewModel: CreatePrayerScreenViewModel): Boolean {
    return createPrayerViewModel.uiState.value.title.isNotEmpty() && createPrayerViewModel.uiState.value.content.isNotEmpty()
}

fun getState(createPrayerViewModel: CreatePrayerScreenViewModel): CreatePrayerScreenState {
    return createPrayerViewModel.uiState.value
}
