package com.mohamedfahmy.taskkoinz.presentation.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mohamedfahmy.taskkoinz.data.model.Items
import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.mohamedfahmy.taskkoinz.domain.usecase.GetItemsUseCase
import com.mohamedfahmy.taskkoinz.domain.usecase.SaveItemsUseCase
import com.tayyar.delivery.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    app: Application,
    private val getItemsUseCase: GetItemsUseCase,
) : AndroidViewModel(app) {

    val items: MutableLiveData<Resource<Items>> = MutableLiveData()
    val numPageViewModel: MutableLiveData<String> = MutableLiveData()
    val setData: MutableLiveData<List<Photo>> = MutableLiveData()

    init {
        numPageViewModel.value = "1"
    }

    fun getItemsFromApi(paramsMap: Map<String, String>) = viewModelScope.launch(Dispatchers.IO) {
        items.postValue(Resource.Loading())
        try {
            val apiResult = getItemsUseCase.execute(paramsMap)
            items.postValue(apiResult)
        } catch (e: Exception) {
            items.postValue(Resource.Error(e.message.toString()))
        }
    }


}





