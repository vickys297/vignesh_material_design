package com.fromsimply.vignesh_material_design.di

import com.fromsimply.vignesh_material_design.data.remote.repository.HoldingsRepository
import com.fromsimply.vignesh_material_design.data.remote.repository.HoldingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    @Singleton
    fun providesHoldingRepository(repository: HoldingsRepositoryImpl): HoldingsRepository {
        return repository
    }
}