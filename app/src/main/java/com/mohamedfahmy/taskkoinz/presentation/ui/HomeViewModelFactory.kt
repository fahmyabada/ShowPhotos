package com.mohamedfahmy.taskkoinz.presentation.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamedfahmy.taskkoinz.domain.usecase.GetItemsUseCase

class HomeViewModelFactory(
    private val app: Application,
    private val getItemsUseCase: GetItemsUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            app,
            getItemsUseCase
        ) as T
    }
}

