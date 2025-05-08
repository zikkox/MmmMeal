package com.example.mmmmeal.presentation.navigation

sealed class Screen(val route: String){
    data object Splash: Screen("splash")
    data object Login: Screen("login")
    data object Home: Screen("home")
    data object Signup: Screen("signup")
}