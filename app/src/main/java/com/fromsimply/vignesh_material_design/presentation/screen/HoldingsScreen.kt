package com.fromsimply.vignesh_material_design.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.fromsimply.vignesh_material_design.R
import com.fromsimply.vignesh_material_design.data.Mapper.HoldingsMapper
import com.fromsimply.vignesh_material_design.presentation.utils.HoldingItem
import com.fromsimply.vignesh_material_design.presentation.utils.NetworkBanner
import com.fromsimply.vignesh_material_design.presentation.utils.PNLBottomPlaceholder
import com.fromsimply.vignesh_material_design.presentation.utils.PNLBottomSheetView
import com.fromsimply.vignesh_material_design.presentation.utils.rememberNetworkState
import com.fromsimply.vignesh_material_design.presentation.viewModel.HoldingsViewModel

@Composable
fun HoldingsScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HoldingsViewModel>()

    val isNetworkConnected = rememberNetworkState()


    val holdingList = viewModel.items.collectAsLazyPagingItems()
    val expanded = rememberSaveable { mutableStateOf(false) }

    val currentValue = viewModel.currentValue.collectAsState()
    val currentTotalInvestmentValue = viewModel.currentTotalInvestmentValue.collectAsState()
    val currentTodayPNLValue = viewModel.currentTodayPNLValue.collectAsState()


    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing = remember { mutableStateOf(false) }

    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.safeDrawingPadding()) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Text(
                    stringResource(R.string.my_holdings),
                    modifier = Modifier
                        .padding(12.dp)
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )

                NetworkBanner(isNetworkConnected)

                AnimatedVisibility(
                    visible = holdingList.loadState.refresh == LoadState.Loading || isRefreshing.value,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                when (holdingList.loadState.refresh) {
                    is LoadState.NotLoading -> {
                        isRefreshing.value = false
                        if (holdingList.itemCount == 0) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = stringResource(R.string.currently_you_do_not_have_holdings),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(24.dp)
                                    )
                                    LottieAnimation(
                                        composition = composition,
                                        progress = { progress },
                                        modifier = Modifier.size(128.dp)
                                    )

                                    if (isNetworkConnected) {
                                        TextButton(onClick = {
                                            viewModel.refreshDataSource()
                                        }, modifier = Modifier.padding(top = 12.dp)) {
                                            Text(text = "Refresh")
                                        }
                                    }
                                }
                            }
                        } else {
                            PullToRefreshBox(
                                isRefreshing = isRefreshing.value,
                                state = pullToRefreshState,
                                onRefresh = {
                                    isRefreshing.value = true
                                    holdingList.refresh()
                                }) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(
                                        holdingList.itemCount, key = { index ->
                                            holdingList.peek(index)?.symbol ?: index
                                        }) { index ->
                                        holdingList[index]?.apply {
                                            HoldingItem(HoldingsMapper.fromEntityToUI(this))
                                        }
                                    }

                                    item {
                                        Spacer(modifier = Modifier.padding(50.dp))
                                    }
                                }
                            }
                        }
                    }

                    is LoadState.Error -> {

                    }

                    is LoadState.Loading -> {

                    }
                }
            }

            if (holdingList.itemCount > 0) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        AnimatedVisibility(
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                            visible = expanded.value
                        ) {
                            PNLBottomSheetView(
                                currentValue = currentValue,
                                currentTotalInvestmentValue = currentTotalInvestmentValue,
                                todayPNL = currentTodayPNLValue
                            )
                        }
                        PNLBottomPlaceholder(
                            pnl = (currentValue.value - currentTotalInvestmentValue.value),
                            expanded = expanded
                        )
                    }
                }
            }
        }
    }
}