package com.fromsimply.vignesh_material_design.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.fromsimply.vignesh_material_design.presentation.navigation.NavigationScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(navController: NavHostController) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main) {
                navController.apply {
                    navigate(NavigationScreen.HomeScreen.route) {
                        popUpTo(route = NavigationScreen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text("Splash Screen")
        }
    }
}