package com.mohamedfahmy.taskkoinz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohamedfahmy.taskkoinz.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(item: List<Photo>)

    @Query("select * from Item order by id")
    fun getItems(): List<Photo>

}