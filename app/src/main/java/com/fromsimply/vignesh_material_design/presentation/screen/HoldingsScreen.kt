package com.fromsimply.vignesh_material_design.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.fromsimply.vignesh_material_design.data.Mapper.HoldingsMapper
import com.fromsimply.vignesh_material_design.presentation.utils.HoldingItem
import com.fromsimply.vignesh_material_design.presentation.viewModel.HoldingsViewModel


@Composable
fun HoldingsScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HoldingsViewModel>()

    val holdingList = viewModel.items.collectAsLazyPagingItems()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Text("Holding Screen")

            LazyColumn {
                items(holdingList.itemCount) { index ->
                    holdingList[index]?.apply {
                        HoldingItem(HoldingsMapper.fromEntityToUI(this))
                    }
                }
            }
        }
    }
}