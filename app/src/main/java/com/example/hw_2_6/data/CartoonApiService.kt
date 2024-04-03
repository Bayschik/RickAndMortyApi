package com.example.hw_2_6.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CartoonApiService {
    @GET("character")
    suspend fun getCharacters():Response<BaseResponse<Character>>

    @GET("character/")
    suspend fun getNextPage(
        @Query("page") page:Int
    ):Response<BaseResponse<Character>>

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id:Int
    ):Response<Character>
}