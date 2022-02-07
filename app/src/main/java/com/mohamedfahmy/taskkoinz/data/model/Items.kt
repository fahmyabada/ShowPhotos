package com.mohamedfahmy.taskkoinz.data.model


import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)