package com.example.prayerbox.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prayerbox.models.Prayer
import java.time.LocalDate

@Composable
fun PrayerCard(prayer: Prayer){
    Card(
        modifier = Modifier.size(300.dp),
        elevation = CardDefaults.cardElevation(4.dp),

    ){
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = prayer.name, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = prayer.dateCreated.toString(),
                    fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.size(8.dp))

            Text(text = prayer.content)


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrayerCardPreview(){
    val prayer = Prayer(
        "My Prayer",
        "Here is my prayer adhbwjka bdkja bwdkj bakjwdb kjawbd kawbdjk bakwjd bakjwbd kjawbd kjabwd kjabwkjd bawkj dbkj",
        LocalDate.parse("2018-02-03") )
    PrayerCard(prayer)
}