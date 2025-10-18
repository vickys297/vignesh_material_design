package com.fromsimply.vignesh_material_design.data.local.repository

import kotlinx.coroutines.flow.Flow

interface LocalHoldingRepository {

    suspend fun getCurrentValueSum(): Flow<Double>

    suspend fun getTotalInvestmentSum():  Flow<Double>

    suspend fun getTodayPNL():  Flow<Double>
}