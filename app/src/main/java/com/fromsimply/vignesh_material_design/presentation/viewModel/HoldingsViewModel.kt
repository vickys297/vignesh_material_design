package com.fromsimply.vignesh_material_design.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.fromsimply.vignesh_material_design.domian.entity.HoldingResponseData
import com.fromsimply.vignesh_material_design.domian.useCase.GetHoldingListUseCase
import com.fromsimply.vignesh_material_design.domian.useCase.GetHoldingPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HoldingsViewModel @Inject constructor(
    private val getHoldingListUseCase: GetHoldingListUseCase, private val getHoldingPagination: GetHoldingPagination
) : ViewModel() {

    val items: Flow<PagingData<HoldingEntity>> = getHoldingPagination().distinctUntilChanged().cachedIn(viewModelScope)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { getHoldingListUseCase() }
        }
    }
}