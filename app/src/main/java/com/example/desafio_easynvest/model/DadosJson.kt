package com.example.desafio_easynvest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.properties.Delegates

class DadosJson : Serializable {
    @SerializedName("status")
    var status : String = "ERRO"
    @SerializedName("")
    var responseSimulator : ResponseSimulator? = null
}