package com.fromsimply.vignesh_material_design.di

import android.content.Context
import androidx.room.RoomDatabase
import com.fromsimply.vignesh_material_design.data.local.database.LocalDatabase
import com.fromsimply.vignesh_material_design.data.local.database.LocalDatabaseConfig
import com.fromsimply.vignesh_material_design.data.remote.network.HoldingApiService
import com.fromsimply.vignesh_material_design.data.remote.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabaseConfig.createDatabase(context = context)
    }

    @Provides
    @Singleton
    fun providesRetrofitService(): HoldingApiService {
        return RetrofitService.createRetrofitInstance()
    }

}