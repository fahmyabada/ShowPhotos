package com.mohamedfahmy.taskkoinz.presentation.di.core

import android.app.Application
import com.mohamedfahmy.taskkoinz.domain.usecase.GetItemsUseCase
import com.mohamedfahmy.taskkoinz.presentation.ui.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideHomeViewModelFactory(
        application: Application,
        getItemsUseCase: GetItemsUseCase,
    ): HomeViewModelFactory {
        return HomeViewModelFactory(application, getItemsUseCase)
    }


}