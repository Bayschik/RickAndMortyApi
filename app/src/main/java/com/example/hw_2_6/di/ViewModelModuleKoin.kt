package com.example.hw_2_6.di

import com.example.hw_2_6.ui.characterDetails.CharacterDetailsViewModel
import com.example.hw_2_6.ui.characters.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        ViewModel(get())
    }

    viewModel{
        CharacterDetailsViewModel(get())
    }
}