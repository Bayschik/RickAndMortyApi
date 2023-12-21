package com.example.hw_2_6.di

import com.example.hw_2_6.BuildConfig
import com.example.hw_2_6.data.CartoonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class CartoonModule {
    @Provides
    fun provideApi():CartoonApi{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartoonApi::class.java)
    }
}