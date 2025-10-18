package com.fromsimply.vignesh_material_design.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fromsimply.vignesh_material_design.R
import kotlinx.coroutines.delay


@Composable
fun rememberNetworkState(): Boolean {


    val context = LocalContext.current
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun checkNetwork(): Boolean {
        return cm.activeNetwork != null
    }

    var isConnected by remember { mutableStateOf(checkNetwork()) }

    DisposableEffect(context) {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected = true
            }

            override fun onLost(network: Network) {
                isConnected = false
            }
        }

        cm.registerDefaultNetworkCallback(callback)
        onDispose {
            cm.unregisterNetworkCallback(callback)
        }
    }
    return isConnected

}


@Composable
fun NetworkBanner(isConnected: Boolean) {
    var showBanner by remember { mutableStateOf(!isConnected) }

    LaunchedEffect(isConnected) {
        if (!isConnected) {
            showBanner = true
        } else {
            delay(3000)
            showBanner = false
        }
    }

    AnimatedVisibility(
        visible = showBanner, enter = expandVertically(), exit = shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_internet_connection), color = Color.White
            )
        }
    }
}
