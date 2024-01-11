package com.example.hw_2_6.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class Repository(private val api: CartoonApiService) {

    fun getCharacters(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = api.getCharacters()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    emit(Resource.Success(it.results))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getCharacterDetails(id: Int): LiveData<Resource<Character>> = liveData(Dispatchers.IO) {
        try {
            val cartoon = api.getCharacterDetails(id)
            if (cartoon.isSuccessful) {
                cartoon.body()?.let {
                    emit(Resource.Success(it))
                }
            }
        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown Error"))
        }

    }

}