package com.example.desafio_easynvest.model.request

import com.example.desafio_easynvest.utils.Constants
import java.io.Serializable
import kotlin.properties.Delegates

class MyResquest : Serializable {
    var investedAmount : Double? = null
    val index = Constants.INDEX
    var rate: Int? = null
    val isTaxFree = Constants.IS_TAX_FREE
    var maturityDate : String? = null

    constructor(investedAmount : Double,rate : Int, maturityDate : String ) {
        this.investedAmount = investedAmount
        this.rate = rate
        this.maturityDate = maturityDate
    }
}