package com.example.desafio_easynvest.api

import com.example.desafio_easynvest.model.DadosJson
import com.example.desafio_easynvest.model.request.MyResquest
import com.example.desafio_easynvest.utils.Util
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.HashMap

class SimulatorService : KoinComponent{
    val api : SimulatorApi by inject()

    fun getSimulation(myRequest : MyResquest): Single<DadosJson> {
        return api.getSimulation(request(myRequest))
    }

    private fun request(request : MyResquest) : HashMap<String, String> {
        return Util.retornaHashJson("=",
            String.format("investedAmount=%s", request.investedAmount.toString()),
            String.format("index=%s", request.index),
            String.format("rate=%s", request.rate.toString()),
            String.format("isTaxFree=%s", request.isTaxFree.toString()),
            String.format("maturityDate=%s", request.maturityDate))
    }
}