package com.example.desafio_easynvest.api.repository

import com.example.desafio_easynvest.api.SimulatorService
import com.example.desafio_easynvest.model.DadosJson
import com.example.desafio_easynvest.model.request.MyResquest
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class SimulatorRepository : KoinComponent{
    private val simulatorService : SimulatorService by inject()
    lateinit var myResquest : MyResquest

    fun getInfo() : Single<DadosJson>{
       return simulatorService.getSimulation(myResquest)
    }
}