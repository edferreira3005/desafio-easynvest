package com.example.desafio_easynvest.application

import android.app.Application
import com.example.desafio_easynvest.api.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EasyApplication  : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@EasyApplication)
            modules(listOf(module))
        }
    }
}