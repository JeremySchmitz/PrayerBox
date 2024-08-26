package com.example.prayerbox.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prayerbox.models.CreatePrayerScreenViewModel
import com.example.prayerbox.models.DrawPrayerScreenViewModel

@Composable
fun PrayerBoxNavigationGraph(
    createPrayerViewModel: CreatePrayerScreenViewModel = viewModel(),
    drawPrayerScreenViewModel: DrawPrayerScreenViewModel = viewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    var selected by remember { mutableStateOf(Icons.Default.Home) }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Color.Gray) {
                IconButton(
                    onClick = {
                        selected = Icons.Default.Home
                        navController.navigate(Screens.Home.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(26.dp),
                        tint = if (selected == Icons.Default.Home) Color.DarkGray else Color.White //TODO Update Colors
                    )
                }

                //TODO Update Icon
                IconButton(
                    onClick = {
                        selected = Icons.Default.Favorite
                        navController.navigate(Screens.Draw.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Draw",
                        modifier = Modifier.size(26.dp),
                        tint = if (selected == Icons.Default.Favorite) Color.DarkGray else Color.White
                    )
                }

                IconButton(
                    onClick = {
                        selected = Icons.Default.Add
                        navController.navigate(Screens.Create.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(26.dp),
                        tint = if (selected == Icons.Default.Add) Color.DarkGray else Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen) { HomeScreen() }

            composable(Screens.Create.screen) { CreatePrayerScreen(createPrayerViewModel) }

            composable(Screens.Draw.screen) { DrawPrayerScreen(drawPrayerScreenViewModel) }
        }
    }
}

@Preview
@Composable
fun PrayerBoxNavigationGraphPreview(){
    PrayerBoxNavigationGraph(viewModel(), viewModel())

}