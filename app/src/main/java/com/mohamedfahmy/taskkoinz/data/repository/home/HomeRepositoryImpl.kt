package com.mohamedfahmy.taskkoinz.data.repository.home

import android.util.Log
import com.mohamedfahmy.taskkoinz.data.api.SessionManager
import com.mohamedfahmy.taskkoinz.data.model.Items
import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository
import com.mohamedfahmy.taskkoinz.presentation.ui.CheckNetworkAvailable
import com.tayyar.delivery.data.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response
import java.lang.Exception

class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeDataBaseDataSource: HomeDataBaseDataSource,
    private val sessionManager: SessionManager,
    private val checkNetworkAvailable: CheckNetworkAvailable
) : HomeRepository {

    override suspend fun getItems(paramsMap: Map<String, String>): Resource<List<Photo>> {
        return getPhotosFromDatabase(paramsMap)
    }

    override suspend fun saveItem(item: List<Photo>) {
        return homeDataBaseDataSource.saveItem(item)
    }


    private suspend fun getItemFromDatabase(): List<Photo> {
        return homeDataBaseDataSource.getItems()
    }

    private suspend fun getPhotosFromDatabase(paramsMap: Map<String, String>): Resource<List<Photo>> {
        lateinit var item: Resource<List<Photo>>
        if (checkNetworkAvailable.isNetworkAvailable()) {
            item =
                responsePhotosFromApiToResource(homeRemoteDataSource.getItemsFromApi(paramsMap))
        } else {
            try {
                item =
                    responsePhotosFromDatabaseToResource(homeDataBaseDataSource.getItems())
            } catch (exception: Exception) {
                Log.i("MyTag", exception.message.toString())
            }
        }

        return item
    }

    private suspend fun responsePhotosFromApiToResource(response: Response<Items>): Resource<List<Photo>> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                sessionManager.putInt("page", (result.photos.page + 1))

                val save = CoroutineScope(Dispatchers.IO).async {
                    saveItem(result.photos.photo)
                }

                save.await()

                return Resource.Success(getItemFromDatabase())
            }
        }
        return Resource.Error(response.message())
    }


    private fun responsePhotosFromDatabaseToResource(response: List<Photo>): Resource<List<Photo>> {
        if (response.isNotEmpty()) {
            return Resource.Success(response)
        }
        return Resource.Error("not found data")
    }
}