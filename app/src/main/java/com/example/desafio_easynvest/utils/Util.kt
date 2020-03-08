package com.example.desafio_easynvest.utils

import android.content.Context
import android.widget.EditText
import com.example.desafio_easynvest.R
import io.reactivex.annotations.Experimental
import java.text.Format
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.DataFormatException

object Util {
    @Throws(Exception::class)
    fun returnHashJson(type: String, vararg params: Any): HashMap<String, String> {
        val mHash = HashMap<String, String>()

        if (params.isNotEmpty()) {
            for (`object` in params) {
                val key = (`object` as String).split(type.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (key.size >= 2)
                    mHash[key[0]] = key[1]

            }
        }
        return mHash
    }

    fun returnFormatedDate(date : String, format : String, oldFormat : String) : String {
        return try {
            val oldFormater = SimpleDateFormat(oldFormat, Locale.ENGLISH)
            val formater = SimpleDateFormat(format, Locale.ENGLISH)
            val oldDate = oldFormater.parse(date)

            formater.format(oldDate!!).toString()
        }catch (e : Throwable){
            ""
        }
    }

    fun returnDoubleRealFromString(value : String) : Double {
        var newValue = value.replace("[R$.\u00A0]".toRegex(), "")
        newValue = newValue.replace(",", ".").replace(" ", "")

        return newValue.toDouble()
    }

    fun returnIntPorcentFromString(value : String) : Int {
        return value.replace("[%]".toRegex(), "").toInt()
    }

    fun returnReaisValue(value : Double) : String {
        val newValue = NumberFormat.getCurrencyInstance().format(value)
        return newValue.replace("R$", "R$ ")
    }

    fun returnPorcentage(value : Double, integer : Boolean) : String {
        return if(integer)
            value.toInt().toString() + "%"
        else
            String.format("%,.2f", value) + "%"
    }

    fun validateNumberValue(editText: EditText, reais : Boolean, replace : String, rate : Int) : Boolean {
        return if(reais) {
            if (returnDoubleRealFromString(editText.text.toString()) <= 0) {
                editText.error = "Valor deve ser maior que zero!"
                editText.requestFocus()
                false
            } else true
        }else{
            val textValue = editText.text.toString().replace(replace,"")
            if(returnIntPorcentFromString(textValue) <= rate) {
                editText.error = "Valor deve ser superior a $rate"
                editText.requestFocus()
                false
            }else true
        }
    }

    fun validateDateValue(editText: EditText) : Boolean {
        return if(returnFormatedDate(editText.text.toString(),"yyyy-MM-dd","dd/MM/yyyy").isEmpty()) {
            editText.error = "Formato de dada inválida"
            editText.requestFocus()
            false
        }else true
    }
}