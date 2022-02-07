package com.mohamedfahmy.taskkoinz.presentation.di.core

import com.mohamedfahmy.taskkoinz.data.dao.ItemDao
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeDataBaseDataSource
import com.mohamedfahmy.taskkoinz.data.repository.home.HomeDataBaseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideHomeDataBaseDataSource(itemDao: ItemDao): HomeDataBaseDataSource {
        return HomeDataBaseDataSourceImpl(itemDao)
    }

}