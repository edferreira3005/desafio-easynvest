package com.example.desafio_easynvest.utils.exception

import android.content.Context
import android.widget.Toast
import com.example.desafio_easynvest.R
import java.lang.Exception

class SimulatorException(var exception: Throwable, var context: Context) : Exception(exception) {

    fun showError(){
            Toast.makeText(context,context.resources.getString(R.string.erro_consulta), Toast.LENGTH_LONG).show()
    }
}