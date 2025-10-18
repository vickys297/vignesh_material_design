package com.fromsimply.vignesh_material_design.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.fromsimply.vignesh_material_design.data.local.usecase.GetCurrentValueUseCase
import com.fromsimply.vignesh_material_design.data.local.usecase.GetTodayPNLUseCase
import com.fromsimply.vignesh_material_design.data.local.usecase.GetTotalInvestmentValueUseCase
import com.fromsimply.vignesh_material_design.domian.useCase.GetHoldingListUseCase
import com.fromsimply.vignesh_material_design.domian.useCase.GetHoldingPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@HiltViewModel
class HoldingsViewModel @Inject constructor(
    private val getHoldingListUseCase: GetHoldingListUseCase,
    getHoldingPagination: GetHoldingPagination,
    private val getCurrentValueUseCase: GetCurrentValueUseCase,
    private val getTotalInvestmentValueUseCase: GetTotalInvestmentValueUseCase,
    private val getTodayPNLUseCase: GetTodayPNLUseCase
) : ViewModel() {
    fun refreshDataSource() {
        viewModelScope.launch(Dispatchers.IO) {
            launch { getHoldingListUseCase() }
        }
    }

    var items: Flow<PagingData<HoldingEntity>> =
        getHoldingPagination().distinctUntilChanged().cachedIn(viewModelScope)


    private val _currentValue = MutableStateFlow(0.0)
    val currentValue = _currentValue.asStateFlow()

    private val _currentTotalInvestmentValue = MutableStateFlow(0.0)
    val currentTotalInvestmentValue = _currentTotalInvestmentValue.asStateFlow()

    private val _currentTodayPNLValue = MutableStateFlow(0.0)
    val currentTodayPNLValue = _currentTodayPNLValue.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { getHoldingListUseCase() }
            launch {
                getCurrentValueUseCase().collectLatest {
                    _currentValue.value = it
                }
            }
            launch {
                getTotalInvestmentValueUseCase().collectLatest {
                    _currentTotalInvestmentValue.value = it
                }
            }
            launch {
                getTodayPNLUseCase().collectLatest {
                    _currentTodayPNLValue.value = it
                }
            }
        }
    }
}