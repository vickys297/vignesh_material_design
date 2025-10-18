package com.fromsimply.vignesh_material_design.data.remote.repository

import androidx.paging.PagingData
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.fromsimply.vignesh_material_design.domian.entity.UserHolding
import kotlinx.coroutines.flow.Flow

interface HoldingsRepository {
    suspend fun fetchUserHoldings(): Result<UserHolding>

    fun userHoldingListPagination(): Flow<PagingData<HoldingEntity>>
}