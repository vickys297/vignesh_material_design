package com.fromsimply.vignesh_material_design.data.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HoldingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<HoldingEntity>)


    @Query("SELECT * FROM USER_HOLDINGS")
    fun getAllHolding(): PagingSource<Int, HoldingEntity>

    @Query("SELECT SUM(ltp * quantity) FROM USER_HOLDINGS")
    fun getCurrentValueSum(): Flow<Double>

    @Query("SELECT SUM(avgPrice * quantity) FROM USER_HOLDINGS")
    fun getTotalInvestmentSum(): Flow<Double>

    @Query("SELECT SUM((close - ltp) * quantity) FROM USER_HOLDINGS")
    fun getTodayPNL(): Flow<Double>
}