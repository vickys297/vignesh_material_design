package com.fromsimply.vignesh_material_design.presentation.navigation

enum class Screens {
    HoldingScreen, SplashScreen
}

sealed class NavigationScreen(val route: String) {

    object HomeScreen : NavigationScreen(Screens.HoldingScreen.name)

    object SplashScreen : NavigationScreen(Screens.SplashScreen.name)
}