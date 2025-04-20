package com.example.mmmmeal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mmmmeal.presentation.screens.LoginScreen
import com.example.mmmmeal.presentation.screens.SplashScreen

class AppNavigation {
    @Composable
    fun navigation(){
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Home.route) {
            }
        }
    }
}