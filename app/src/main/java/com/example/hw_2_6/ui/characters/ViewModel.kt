package com.example.hw_2_6.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_2_6.data.Character
import com.example.hw_2_6.data.Repository
import com.example.hw_2_6.data.Resource

class ViewModel(private val repository: Repository):ViewModel(){
    suspend fun getCharacters():LiveData<Resource<List<Character>>> = repository.getCharacters()
    suspend fun getNextPage(page:Int):LiveData<Resource<List<Character>>> = repository.getNextPage(page)
}