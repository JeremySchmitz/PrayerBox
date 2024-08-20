package com.example.prayerbox.screens

import android.widget.HorizontalScrollView
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prayerbox.models.CreatePrayerScreenViewModel
import com.example.prayerbox.models.Prayer
import com.example.prayerbox.widgets.PrayerCard
import com.example.prayerbox.widgets.TextInput
import java.time.LocalDate
import java.util.Date

@Composable
fun CreatePrayerScreen(createPrayerViewModel: CreatePrayerScreenViewModel) {
    var title = remember { mutableStateOf("") }
    var content = remember { mutableStateOf("") }
    var prayers = remember { mutableStateListOf<Prayer>() }

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Column {
            Text(text = Routes.CREATE_PRAYER_SCREEN)

            Spacer(modifier = Modifier.size(16.dp))

            TextInput("Prayer Title", {title.value =it})

            Spacer(modifier = Modifier.size(16.dp))

            TextInput("Prayer Content", {content.value = it}, 180.dp)

            Spacer(modifier = Modifier.size(16.dp))

            Button(onClick = {

                prayers.add(Prayer(title.value, content.value, LocalDate.parse("2018-02-03")))
                title.value = ""
                content.value = ""
                println(" ------ submit button")
                println(" ------ submit button" + prayers.size)
            }) {
                Text(text = "Submit")
            }

            Spacer(modifier = Modifier.size(16.dp))


            Box (modifier = Modifier.fillMaxSize()){

                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    for (prayer in prayers){
                        Box(modifier = Modifier.padding(8.dp)){
                            PrayerCard(prayer = prayer)

                        }

                    }

                }


            }
        }



    }
}


@Preview
@Composable
fun CreatePrayerScreenPreview(){
    CreatePrayerScreen(createPrayerViewModel = viewModel())
}
