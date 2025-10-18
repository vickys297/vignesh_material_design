package com.fromsimply.vignesh_material_design.domian.useCase

import androidx.paging.PagingData
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.fromsimply.vignesh_material_design.data.remote.repository.HoldingsRepository
import com.fromsimply.vignesh_material_design.domian.entity.UserHolding
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoldingListUseCase @Inject constructor(private val repository: HoldingsRepository) {
    suspend operator fun invoke(): Result<UserHolding> {
        return repository.fetchUserHoldings()
    }
}

class GetHoldingPagination @Inject constructor(private val repository: HoldingsRepository) {
    operator fun invoke(): Flow<PagingData<HoldingEntity>> {
        return repository.userHoldingListPagination()
    }
}
