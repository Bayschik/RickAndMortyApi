package com.example.hw_2_6.di

import com.example.hw_2_6.BuildConfig
import com.example.hw_2_6.data.CartoonApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideCartoonApiService(
    retrofit: Retrofit
): CartoonApiService {
    return retrofit.create(CartoonApiService::class.java)
}