package com.example.hw_2_6.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_2_6.data.Character
import com.example.hw_2_6.data.Repository
import com.example.hw_2_6.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){
    fun getCharacters():LiveData<Resource<List<Character>>> = repository.getCharacters()
}