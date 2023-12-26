package com.example.hw_2_6.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CartoonApiService {
    @GET("character")
    fun getCharacters():Call<BaseResponse<Character>>

    @GET("character/{id}")
    fun getCharacterDetails(
        @Path("id") id:Int
    ):Call<Character>
}