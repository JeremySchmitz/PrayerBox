package com.example.prayerbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prayerbox.screens.CreatePrayerScreen
import com.example.prayerbox.screens.HomeScreen
import com.example.prayerbox.screens.PrayerBoxNavigationGraph
import com.example.prayerbox.screens.Routes
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