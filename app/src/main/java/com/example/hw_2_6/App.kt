package com.example.hw_2_6

import android.app.Application
import com.example.hw_2_6.di.cartoonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(cartoonModule)
        }
    }
}