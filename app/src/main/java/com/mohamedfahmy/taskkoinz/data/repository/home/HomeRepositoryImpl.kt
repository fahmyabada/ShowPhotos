package com.mohamedfahmy.taskkoinz.data.repository.home

import android.util.Log
import com.mohamedfahmy.taskkoinz.data.model.Items
import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.mohamedfahmy.taskkoinz.data.model.Photos
import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository
import com.mohamedfahmy.taskkoinz.presentation.ui.CheckNetworkAvailable
import com.tayyar.delivery.data.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeDataBaseDataSource: HomeDataBaseDataSource,
    private val checkNetworkAvailable: CheckNetworkAvailable
) : HomeRepository {

    override suspend fun getItems(paramsMap: Map<String, String>): Resource<Items> {
        return getPhotosFromDatabase(paramsMap)
    }

    override suspend fun saveItem(item: List<Photo>) {
        return homeDataBaseDataSource.saveItem(item)
    }

    private suspend fun getPhotosFromDatabase(paramsMap: Map<String, String>): Resource<Items> {
        lateinit var photoList: List<Photo>
        lateinit var item: Resource<Items>
        if (checkNetworkAvailable.isNetworkAvailable()) {
            item =
                responsePhotosFromApiToResource(homeRemoteDataSource.getItemsFromApi(paramsMap))
        } else {
            try {
                photoList = homeDataBaseDataSource.getItems()
                item =
                    responsePhotosFromDatabaseToResource(Items(Photos(1, 1, 1, photoList, 1), "ok"))
            } catch (exception: Exception) {
                Log.i("MyTag", exception.message.toString())
            }
        }

        return item
    }

    private fun responsePhotosFromApiToResource(response: Response<Items>): Resource<Items> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveItem(result.photos.photo)
                }
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responsePhotosFromDatabaseToResource(response: Items): Resource<Items> {
        if (response.photos.photo.isNotEmpty()) {
            return Resource.Success(response)
        }
        return Resource.Error("not found data")
    }
}