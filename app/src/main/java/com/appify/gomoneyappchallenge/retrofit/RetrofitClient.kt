package com.appify.gomoneyappchallenge.retrofit

import com.appify.gomoneyappchallenge.utils.SupportInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private val retrofit:Retrofit by lazy {
        val client = OkHttpClient.Builder().addInterceptor(SupportInterceptor()).build()
        Retrofit.Builder()
                .baseUrl("https://api.football-data.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build()
    }

    val client:RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }
}