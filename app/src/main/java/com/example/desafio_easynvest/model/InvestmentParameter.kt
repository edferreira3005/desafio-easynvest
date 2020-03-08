package com.example.desafio_easynvest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class InvestmentParameter : Serializable {
    @SerializedName("investedAmount")
    var investedAmount : Double? = null
    @SerializedName("yearlyInterestRate")
    var yearlyInterestRate : Double? = null
    @SerializedName("maturityTotalDays")
    var maturityTotalDays : Int? = null
    @SerializedName("maturityBusinessDays")
    var maturityBusinessDays : Int? = null
    @SerializedName("maturityDate")
    var maturityDate : String? = null
    @SerializedName("rate")
    var rate : Double? = null
    @SerializedName("isTaxFree")
    var isTaxFree : Boolean? = null
}