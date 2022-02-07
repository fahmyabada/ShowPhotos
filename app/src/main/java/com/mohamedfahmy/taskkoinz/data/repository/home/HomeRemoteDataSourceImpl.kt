package com.mohamedfahmy.taskkoinz.data.repository.home

import com.mohamedfahmy.taskkoinz.data.api.Api
import com.mohamedfahmy.taskkoinz.data.model.Items
import retrofit2.Response

class HomeRemoteDataSourceImpl(private val api: Api) : HomeRemoteDataSource {

    override suspend fun getItemsFromApi(paramsMap: Map<String, String>): Response<Items> {
        return api.getItems(paramsMap)
    }
}