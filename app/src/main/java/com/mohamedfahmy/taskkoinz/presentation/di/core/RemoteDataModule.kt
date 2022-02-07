package com.mohamedfahmy.taskkoinz.presentation.di.core

import com.mohamedfahmy.taskkoinz.data.api.Api
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeRemoteDataSource
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// for all data source remote
@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideHomeRemoteDataSource(api: Api): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(api)
    }
}
