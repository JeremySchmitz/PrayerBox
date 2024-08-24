package com.example.prayerbox.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prayerbox.models.CreatePrayerScreenViewModel
import com.example.prayerbox.models.Prayer
import com.example.prayerbox.widgets.PrayerCard
import com.example.prayerbox.widgets.TextInput
import java.time.LocalDate

@Composable
fun CreatePrayerScreen(viewModel: CreatePrayerScreenViewModel) {
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Text(text = Routes.CREATE_PRAYER_SCREEN)

            Spacer(modifier = Modifier.size(16.dp))

            TextInput(
                value = viewModel.title,
                title = "Prayer Title", singleLine = true, onTextChange = {
                    viewModel.title = it
                },
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(16.dp))

            TextInput(
                modifier = Modifier.height(180.dp),
                value = viewModel.content,
                title = "Prayer Content",
                onTextChange = {
                    viewModel.content = it
                })

            Spacer(modifier = Modifier.size(8.dp))

            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.weight(1F))

                Button(
                    enabled = viewModel.addEnabled(),
                    onClick = {
                        val newPrayer = Prayer(
                            viewModel.title,
                            viewModel.content,
                            //TODO Update Date Creation
                            LocalDate.parse("2018-02-03")
                        )
                        viewModel.add(newPrayer)
                        focusManager.clearFocus()
                        println(" ------ add button")
                        println(" ------ add button " + viewModel.prayers.size)
                    },
                ) {
                    Text(text = "Add Prayer")
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Box(modifier = Modifier.height(300.dp)) {
                LazyRow {
                    items(viewModel.prayers) {
                        Box(modifier = Modifier.padding(8.dp)) {
                            PrayerCard(prayer = it, onDelete = {
                                viewModel.remove(it)
                            })
                        }

                    }

                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.weight(1F))

                //TODO disable if no prayers entered
                Button(onClick = {
                    println(" ------ submit button")
                    println(" ------ submit button " + viewModel.prayers.size)
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
    CreatePrayerScreen(viewModel())
}
