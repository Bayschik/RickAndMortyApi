package com.example.hw_2_6.data

import androidx.lifecycle.LiveData
import com.example.hw_2_6.ui.base.BaseRepository

class Repository(private val api: CartoonApiService):BaseRepository() {

    suspend fun getCharacters(): LiveData<Resource<List<Character>>> = performRequest {
        api.getCharacters().body()?.results ?: emptyList()
    }

    suspend fun getNextPage(page:Int): LiveData<Resource<List<Character>>> = performRequest {
        api.getNextPage(page).body()?.results ?: emptyList()
    }

    suspend fun getCharacterDetails(id: Int): LiveData<Resource<Character>> = performRequest {
        api.getCharacterDetails(id).body()!!
    }
}