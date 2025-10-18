package com.fromsimply.vignesh_material_design.data.local.repository

import com.fromsimply.vignesh_material_design.data.local.database.LocalDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalHoldingRepositoryImpl @Inject constructor(localDatabase: LocalDatabase) :
    LocalHoldingRepository {

    private val holdingDao = localDatabase.holdingsDao()

    override suspend fun getCurrentValueSum(): Flow<Double> {
        return holdingDao.getCurrentValueSum()
    }

    override suspend fun getTotalInvestmentSum(): Flow<Double> {
        return holdingDao.getTotalInvestmentSum()
    }


    override suspend fun getTodayPNL(): Flow<Double> {
        return holdingDao.getTodayPNL()
    }
}