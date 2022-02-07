package com.mohamedfahmy.taskkoinz.presentation.di.core

import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository
import com.mohamedfahmy.taskkoinz.domain.usecase.GetItemsUseCase
import com.mohamedfahmy.taskkoinz.domain.usecase.SaveItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetItemsUseCase(homeRepository: HomeRepository): GetItemsUseCase {
        return GetItemsUseCase(homeRepository)
    }

    @Provides
    fun provideSaveItemsUseCase(homeRepository: HomeRepository): SaveItemsUseCase {
        return SaveItemsUseCase(homeRepository)
    }

}