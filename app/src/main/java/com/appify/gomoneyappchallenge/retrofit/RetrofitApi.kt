package com.appify.gomoneyappchallenge.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("matches/")
    fun getTodayMatches(): Call<String>

    @GET("competition")
    fun getAllCompetitions():Call<String>
}