package com.mohamedfahmy.taskkoinz.data.repository.home

import com.mohamedfahmy.taskkoinz.data.dao.ItemDao
import com.mohamedfahmy.taskkoinz.data.model.Photo

class HomeDataBaseDataSourceImpl(private val itemDao: ItemDao) : HomeDataBaseDataSource {

    override suspend fun getItems(): List<Photo> {
        return itemDao.getItems()
    }

    override suspend fun saveItem(item: List<Photo>) {
        return itemDao.saveItem(item)
    }
}