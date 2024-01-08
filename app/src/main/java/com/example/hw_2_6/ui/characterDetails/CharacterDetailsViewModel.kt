package com.example.hw_2_6.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_2_6.data.Character
import com.example.hw_2_6.data.Repository

class CharacterDetailsViewModel(
    private val repository: Repository
) : ViewModel() {
    fun getCharacterDetails(sendId: Int): LiveData<Character> =
        repository.getCharacterDetails(sendId)
}