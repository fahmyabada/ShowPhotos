package com.mohamedfahmy.taskkoinz.data.api

import okhttp3.OkHttpClient

class HeaderInterceptor {

    fun intercept(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .method(chain.request().method, chain.request().body)
                    .build()
                chain.proceed(newRequest)
            }.build()
    }
}