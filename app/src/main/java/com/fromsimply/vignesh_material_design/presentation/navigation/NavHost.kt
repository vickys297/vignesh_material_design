package com.fromsimply.vignesh_material_design.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fromsimply.vignesh_material_design.presentation.screen.HoldingsScreen
import com.fromsimply.vignesh_material_design.presentation.screen.SplashScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreen.SplashScreen.route) {
        composable(route = NavigationScreen.HomeScreen.route) {
            HoldingsScreen(navController = navController)
        }

        composable(route = NavigationScreen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
    }
}