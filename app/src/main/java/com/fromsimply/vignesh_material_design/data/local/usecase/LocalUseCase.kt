package com.fromsimply.vignesh_material_design.data.local.usecase

import com.fromsimply.vignesh_material_design.data.local.repository.LocalHoldingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentValueUseCase @Inject constructor(private val localHoldingRepository: LocalHoldingRepository) {
    suspend operator fun invoke(): Flow<Double> {
        return localHoldingRepository.getCurrentValueSum()
    }
}

class GetTotalInvestmentValueUseCase @Inject constructor(private val localHoldingRepository: LocalHoldingRepository) {
    suspend operator fun invoke(): Flow<Double> {
        return localHoldingRepository.getTotalInvestmentSum()
    }
}

class GetTodayPNLUseCase @Inject constructor(private val localHoldingRepository: LocalHoldingRepository) {
    suspend operator fun invoke(): Flow<Double> {
        return localHoldingRepository.getTodayPNL()
    }
}