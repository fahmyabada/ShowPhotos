package com.mohamedfahmy.taskkoinz.domain.repository

import com.mohamedfahmy.taskkoinz.data.model.Items
import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.tayyar.delivery.data.util.Resource

interface HomeRepository {
    suspend fun getItems(paramsMap: Map<String, String>): Resource<Items>
    suspend fun saveItem(item: List<Photo>)
}