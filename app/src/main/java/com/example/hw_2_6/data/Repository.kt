package com.example.hw_2_6.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: CartoonApiService
) {

    fun getCharacters(): MutableLiveData<Resource<List<Character>>> {
        val cartoon = MutableLiveData<Resource<List<Character>>>()
        cartoon.postValue(Resource.Loading())

        api.getCharacters().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(call: Call<BaseResponse<Character>>, response: Response<BaseResponse<Character>>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        cartoon.postValue(Resource.Success(it.results))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                cartoon.postValue(Resource.Error(t.localizedMessage ?: "Непредвиденная ошибка"))
            }
        })
        return cartoon
    }

    fun getCharacterDetails(id:Int): MutableLiveData<Character> {
        val cartoon = MutableLiveData<Character>()

        api.getCharacterDetails(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cartoon.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d("ERROR", "данные не пришли")
            }
        })
        return cartoon
    }

}