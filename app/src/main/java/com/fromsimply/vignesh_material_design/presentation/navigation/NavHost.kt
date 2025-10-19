package com.fromsimply.vignesh_material_design.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fromsimply.vignesh_material_design.presentation.screen.HoldingsScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreen.HomeScreen.route) {
        composable(route = NavigationScreen.HomeScreen.route) {
            HoldingsScreen()
        }
    }
}