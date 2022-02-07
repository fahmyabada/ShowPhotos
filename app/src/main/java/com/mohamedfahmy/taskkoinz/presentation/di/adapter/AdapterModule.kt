package com.mohamedfahmy.taskkoinz.presentation.di.adapter


import com.mohamedfahmy.taskkoinz.presentation.ui.CustomHolderHome
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideCustomHolderHome():CustomHolderHome{
        return CustomHolderHome()
    }
}