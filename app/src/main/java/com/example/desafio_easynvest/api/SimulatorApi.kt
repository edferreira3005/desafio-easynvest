package com.example.desafio_easynvest.api

import com.example.desafio_easynvest.model.DadosJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.HashMap

interface SimulatorApi {
    @GET("/calculator/simulate")
    fun getSimulation(@QueryMap data: HashMap<String, String>): Single<DadosJson>
}