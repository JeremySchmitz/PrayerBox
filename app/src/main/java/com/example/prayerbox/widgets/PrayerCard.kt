package com.example.prayerbox.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun PrayerCard(
    prayer: Prayer,
    onDelete: ((value: Prayer) -> Unit)? = null,
    onAnswered: ((value: Prayer) -> Unit)? = null,
) {
    Card(
        modifier = Modifier.size(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (prayer.dateAnswered != null)
                MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),

            ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = prayer.name, fontWeight = FontWeight.Medium, fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = prayer.dateCreated.toString(),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Text(text = prayer.content)

            if (prayer.dateAnswered != null) {
                Spacer(modifier = Modifier.size(8.dp))

                Text(text = "Answered On ${prayer.dateAnswered}", fontWeight = FontWeight.Medium)

                if (prayer.contentAnswered.isNotEmpty()) Text(text = prayer.contentAnswered)
            }

            Spacer(
                modifier = Modifier
                    .size(8.dp)
                    .weight(1f)
            )

            if (onDelete != null) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { onDelete(prayer) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(text = "Delete", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.size(4.dp))
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            } else if (onAnswered != null && prayer.dateAnswered == null) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { onAnswered(prayer) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Text(text = "Answered", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.size(4.dp))
                        //TODO Custom Icon ?
                        Icon(Icons.Default.Favorite, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrayerCardPreview() {
    val prayer = Prayer(
        "My Prayer",
        "Here is my prayer adhbwjka bdkja bwdkj bakjwdb kjawbd kawbdjk bakwjd bakjwbd kjawbd kjabwd kjabwkjd bawkj dbkj",
        LocalDate.parse("2018-02-03"),

        )
    prayer.dateAnswered = LocalDate.parse("2018-02-04")
    prayer.contentAnswered = "Prayer was answered"
    PrayerCard(prayer, onAnswered = {})
}