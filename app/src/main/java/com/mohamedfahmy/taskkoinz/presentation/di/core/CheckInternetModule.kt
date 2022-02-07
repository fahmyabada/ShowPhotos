package com.mohamedfahmy.taskkoinz.presentation.di.core

import android.app.Application
import com.mohamedfahmy.taskkoinz.presentation.ui.CheckNetworkAvailable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CheckInternetModule {

    @Singleton
    @Provides
    fun provideCheckInternet(
        application: Application
    ): CheckNetworkAvailable {
        return CheckNetworkAvailable(application)
    }
}