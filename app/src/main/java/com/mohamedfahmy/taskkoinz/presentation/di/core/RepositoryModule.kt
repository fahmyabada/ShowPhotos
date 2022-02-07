package com.mohamedfahmy.taskkoinz.presentation.di.core

import com.mohamedfahmy.taskkoinz.data.api.SessionManager
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeDataBaseDataSource
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeRemoteDataSource
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeRepositoryImpl
import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository
import com.mohamedfahmy.taskkoinz.presentation.ui.CheckNetworkAvailable
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
    fun provideHomeRepository(
        homeDataBaseDataSource: HomeDataBaseDataSource,
        homeRemoteDataSource: HomeRemoteDataSource,
        sessionManager: SessionManager,
        checkNetworkAvailable: CheckNetworkAvailable
    ): HomeRepository {

        return HomeRepositoryImpl(
            homeRemoteDataSource,
            homeDataBaseDataSource,
            sessionManager,
            checkNetworkAvailable
        )
    }

}