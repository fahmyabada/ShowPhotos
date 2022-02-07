package com.mohamedfahmy.taskkoinz.data.repository.home

import com.mohamedfahmy.taskkoinz.data.model.Photo

interface HomeDataBaseDataSource {
    suspend fun getItems(): List<Photo>
    suspend fun saveItem(item: List<Photo>)
}