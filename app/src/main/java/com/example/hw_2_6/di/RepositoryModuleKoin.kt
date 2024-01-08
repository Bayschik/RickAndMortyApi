package com.example.hw_2_6.di

import com.example.hw_2_6.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single{
        Repository(get())
    }
}