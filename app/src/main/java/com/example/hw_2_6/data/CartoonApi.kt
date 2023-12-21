package com.example.hw_2_6.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CartoonApi {
    @GET("character")
    fun getCartoon():Call<CartoonModel>
}