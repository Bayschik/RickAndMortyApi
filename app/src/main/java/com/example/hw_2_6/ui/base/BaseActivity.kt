package com.example.hw_2_6.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.hw_2_6.data.Resource
import com.example.hw_2_6.ui.utils.showToast

abstract class BaseActivity : AppCompatActivity() {

    fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit) ?= null
    ) {
        observe(this@BaseActivity) { res ->
            state?.invoke(res)
            when (res) {
                is Resource.Error -> {this@BaseActivity.showToast(res.message!!)}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if(res.data != null){
                        success(res.data)
                    }
                }
            }
        }
    }


}
