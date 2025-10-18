package com.fromsimply.vignesh_material_design.data.remote.network

import com.fromsimply.vignesh_material_design.domian.entity.HoldingResponseData
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

public interface HoldingApiService {

    @GET("/")
    suspend fun fetchHoldings(): Response<HoldingResponseData>

}