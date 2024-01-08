package com.example.hw_2_6.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getCharacters():Response<BaseResponse<Character>>

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id:Int
    ):Response<Character>
}