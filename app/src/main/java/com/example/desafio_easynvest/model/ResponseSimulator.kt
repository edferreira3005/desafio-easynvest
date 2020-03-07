package com.example.desafio_easynvest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseSimulator : Serializable {
    @SerializedName("investmentParameter")
    var investmentParameter : InvestmentParameter? = null
    @SerializedName("grossAmount")
    var grossAmount : Double? = null
    @SerializedName("taxesAmount")
    var taxesAmount : Double? = null
    @SerializedName("netAmount")
    var netAmount : Double? = null
    @SerializedName("grossAmountProfit")
    var grossAmountProfit : Double? = null
    @SerializedName("netAmountProfit")
    var netAmountProfit : Double? = null
    @SerializedName("annualGrossRateProfit")
    var annualGrossRateProfit : Double? = null
    @SerializedName("monthlyGrossRateProfit")
    var monthlyGrossRateProfit : Double? = null
    @SerializedName("dailyGrossRateProfit")
    var dailyGrossRateProfit : Float? = null
    @SerializedName("taxesRate")
    var taxesRate : Double? = null
    @SerializedName("rateProfit")
    var rateProfit : Double? = null
    @SerializedName("annualNetRateProfit")
    var annualNetRateProfit : Double? = null
}