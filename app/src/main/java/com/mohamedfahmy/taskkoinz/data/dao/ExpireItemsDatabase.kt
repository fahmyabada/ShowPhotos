package com.mohamedfahmy.taskkoinz.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohamedfahmy.taskkoinz.data.model.Photo

@Database(entities = [Photo::class], version = 1 , exportSchema = false)
abstract class ExpireItemsDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private var db_instance: ExpireItemsDatabase? = null

        fun getAPPDatabaseInstance(context: Context): ExpireItemsDatabase {
            if (db_instance == null) {
                db_instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpireItemsDatabase::class.java,
                    "Items.db"
                ).build()
            }

            return db_instance!!
        }
    }
}