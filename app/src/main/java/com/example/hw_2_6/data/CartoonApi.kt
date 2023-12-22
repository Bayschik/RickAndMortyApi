package com.example.hw_2_6.data

import retrofit2.Call
import retrofit2.http.GET

interface CartoonApi {
    @GET("character")
    fun getCartoon():Call<CartoonModel>
}