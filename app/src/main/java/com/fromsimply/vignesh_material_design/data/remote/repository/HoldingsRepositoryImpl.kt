package com.fromsimply.vignesh_material_design.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.RoomDatabase
import com.fromsimply.vignesh_material_design.data.Mapper.HoldingsMapper
import com.fromsimply.vignesh_material_design.data.local.database.LocalDatabase
import com.fromsimply.vignesh_material_design.data.local.database.LocalDatabaseConfig
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.fromsimply.vignesh_material_design.data.remote.network.HoldingApiService
import com.fromsimply.vignesh_material_design.data.remote.network.RetrofitService
import com.fromsimply.vignesh_material_design.domian.entity.HoldingResponseData
import com.fromsimply.vignesh_material_design.domian.entity.UserHolding
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import okio.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

class HoldingsRepositoryImpl @Inject constructor(
    private val api: HoldingApiService,
    private val localDatabase: LocalDatabase,
) : HoldingsRepository {

    private val holdingDao = localDatabase.holdingsDao()

    override suspend fun fetchUserHoldings(): Result<UserHolding> {
        return try {
            val response = api.fetchHoldings()
            println("fetchUserHoldings >> $response")
            if (response.isSuccessful) {
                response.body()?.data?.let { data ->
                    holdingDao.insertAll(data.userHolding.map { HoldingsMapper.fromDtoToEntity(it) })
                    Result.success(data)
                } ?: Result.failure(IllegalStateException("Successful response with null body/data."))
            } else {
                val errorBody = response.errorBody()?.string()
                println("errorBody >> $errorBody")
                Result.failure(Exception("Something went wrong"))
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val errorMessage = when (e) {
                is UnknownHostException -> "No internet connection or invalid host."
                is SocketTimeoutException -> "Connection timed out."
                is java.io.IOException -> "A network error occurred."
                else -> "An unexpected error occurred."
            }
            Result.failure(Exception(errorMessage, e))
        }
    }

    override fun userHoldingListPagination(): Flow<PagingData<HoldingEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, initialLoadSize = 5, enablePlaceholders = true
                , prefetchDistance = 3
            ), pagingSourceFactory = { localDatabase.holdingsDao().getAllHolding() }).flow
    }
}