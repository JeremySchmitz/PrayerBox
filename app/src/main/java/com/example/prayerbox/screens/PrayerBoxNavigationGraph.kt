package com.example.prayerbox.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prayerbox.models.CreatePrayerScreenViewModel

@Composable
fun PrayerBoxNavigationGraph(createPrayerViewModel: CreatePrayerScreenViewModel = viewModel()){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.CREATE_PRAYER_SCREEN ) {
        composable(Routes.HOME_SCREEN){
            HomeScreen()
        }

        composable(Routes.CREATE_PRAYER_SCREEN){
            CreatePrayerScreen(createPrayerViewModel)
        }

    }
}