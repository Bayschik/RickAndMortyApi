package com.example.hw_2_6.di

import org.koin.dsl.module

val networkModule = module {
    //@Singleton = single
    single {
        provideLoggingInterceptor()
    }

    single {
        provideOkHttpClient(get())
    }

    //Provides = factory
    factory {
        provideRetrofit(get())
    }

    factory {
        provideCartoonApiService(get())
    }
}