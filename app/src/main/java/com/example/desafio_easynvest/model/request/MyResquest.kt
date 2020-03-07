package com.example.desafio_easynvest.model.request

import com.example.desafio_easynvest.utils.Constants
import java.io.Serializable
import kotlin.properties.Delegates

class MyResquest : Serializable {
    var investedAmount by Delegates.notNull<Double>()
    val index = Constants.INDEX
    var rate by Delegates.notNull<Int>()
    val isTaxFree = Constants.IS_TAX_FREE
    var maturityDate by Delegates.notNull<String>()

    constructor(investedAmount : Double,rate : Int, maturityDate : String ) {
        this.investedAmount = investedAmount
        this.rate = rate
        this.maturityDate = maturityDate
    }
}