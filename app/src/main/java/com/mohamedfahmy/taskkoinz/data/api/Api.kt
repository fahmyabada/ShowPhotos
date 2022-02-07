package com.mohamedfahmy.taskkoinz.data.api


import com.mohamedfahmy.taskkoinz.data.model.Items
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("rest")
    suspend fun getItems(
        @QueryMap paramsMap: Map<String, String>
    ): Response<Items>

}