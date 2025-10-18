package com.fromsimply.vignesh_material_design.data.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitService {

    companion object {
        const val BASE_URL = "https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io"

        fun initHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            return client.build()
        }

        fun createRetrofitInstance(): HoldingApiService {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(initHttpClient()).build()

            val service: HoldingApiService = retrofit.create<HoldingApiService>()
            return service
        }
    }
}