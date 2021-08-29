package com.bn.kotlinproject.webservices

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.String


class  WebService {

    fun getAPI(): API {
        val retrofit = Retrofit.Builder()
            .baseUrl(URLS.MainAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(API::class.java)
        return service
    }


}