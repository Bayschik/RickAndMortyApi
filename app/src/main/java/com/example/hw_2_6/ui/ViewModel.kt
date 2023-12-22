package com.example.hw_2_6.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_2_6.data.CartoonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository:Repository):ViewModel(){
    fun getLiveCartoon():LiveData<CartoonModel>{
        return repository.getData()
    }
}