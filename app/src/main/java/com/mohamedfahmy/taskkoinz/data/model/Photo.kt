package com.mohamedfahmy.taskkoinz.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Item")
data class Photo(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "farm")
    @SerializedName("farm")
    val farm: Int,

    @ColumnInfo(name = "isfamily")
    @SerializedName("isfamily")
    val isfamily: Int,

    @ColumnInfo(name = "isfriend")
    @SerializedName("isfriend")
    val isfriend: Int,

    @ColumnInfo(name = "ispublic")
    @SerializedName("ispublic")
    val ispublic: Int,

    @ColumnInfo(name = "owner")
    @SerializedName("owner")
    val owner: String,

    @ColumnInfo(name = "secret")
    @SerializedName("secret")
    val secret: String,

    @ColumnInfo(name = "server")
    @SerializedName("server")
    val server: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String
)