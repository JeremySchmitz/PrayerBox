package com.example.prayerbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.prayerbox.models.CreatePrayerScreenViewModel
import com.example.prayerbox.models.DrawPrayerScreenViewModel
import com.example.prayerbox.models.database.PrayerDatabase
import com.example.prayerbox.screens.PrayerBoxNavigationGraph
import com.example.prayerbox.ui.theme.PrayerBoxTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PrayerDatabase::class.java,
            "prayers.db"
        )
//             NOTE: Leaving this here since Im not sure the Auto migration is set up
//                .addMigrations(MIGRATION_1_2)
            .build()
    }

    // TODO Updated with dependency Injection
    private val createViewModel by viewModels<CreatePrayerScreenViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CreatePrayerScreenViewModel(db.dao) as T
                }
            }
        }
    )

//     TODO Updated with dependency Injection
    private val drawViewModel by viewModels<DrawPrayerScreenViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DrawPrayerScreenViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayerBoxTheme {
                PrayerBoxApp(createViewModel, drawViewModel)
            }
        }
    }
}

@Composable
fun PrayerBoxApp(
    createViewModel: CreatePrayerScreenViewModel,
    drawViewModel: DrawPrayerScreenViewModel
) {
    PrayerBoxNavigationGraph(createViewModel, drawViewModel)
}