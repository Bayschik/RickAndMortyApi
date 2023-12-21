package com.example.hw_2_6.ui

import androidx.lifecycle.MutableLiveData
import com.example.hw_2_6.data.CartoonApi
import com.example.hw_2_6.data.CartoonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api:CartoonApi) {

    fun getData():MutableLiveData<CartoonModel>{
        val cartoon = MutableLiveData<CartoonModel>()

        api.getCartoon().enqueue(object :Callback<CartoonModel>{
            override fun onResponse(call: Call<CartoonModel>, response: Response<CartoonModel>) {
                if (response.isSuccessful){
                    response.body().let {
                        if (it != null) {
                            cartoon.postValue(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<CartoonModel>, t: Throwable) {}
        })
        return cartoon
    }

}