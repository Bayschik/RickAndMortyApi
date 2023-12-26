package com.example.hw_2_6.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_2_6.data.Character
import com.example.hw_2_6.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){
    fun getCharacterDetails(sendId:Int): LiveData<Character> =  repository.getCharacterDetails(sendId)
}