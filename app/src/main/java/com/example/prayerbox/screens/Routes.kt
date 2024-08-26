package com.example.prayerbox.screens

sealed class Screens (val screen: String) {
    data object Home: Screens("HOME")
    data object Create: Screens("CREATE")
    data object Draw: Screens("DRAW")
}