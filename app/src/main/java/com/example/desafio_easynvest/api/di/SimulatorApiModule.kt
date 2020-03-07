package com.example.desafio_easynvest.api.di

import com.example.desafio_easynvest.api.SimulatorApi
import com.example.desafio_easynvest.api.SimulatorService
import com.example.desafio_easynvest.api.repository.SimulatorRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://api-simulator-calc.easynvest.com.br"

val module = module {
    single(named("Repository")) { SimulatorRepository() }
    factory(named("Api")) { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SimulatorApi::class.java) }
    single(named("Service")) { SimulatorService() }
}