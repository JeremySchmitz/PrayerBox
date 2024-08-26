package com.example.prayerbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.prayerbox.screens.PrayerBoxNavigationGraph
import com.example.prayerbox.ui.theme.PrayerBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayerBoxTheme {
                PrayerBoxApp()
            }
        }
    }
}

@Composable
fun PrayerBoxApp(){
    PrayerBoxNavigationGraph()
}