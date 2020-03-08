package com.example.desafio_easynvest.utils

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

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
        val oldFormater = SimpleDateFormat(oldFormat, Locale.ENGLISH)
        val formater = SimpleDateFormat(format,Locale.ENGLISH)
        val oldDate = oldFormater.parse(date)

        return formater.format(oldDate!!).toString()
    }

    fun returnDoubleRealFromString(value : String) : Double {
        var newValue = value.replace("[R$.\u00A0]".toRegex(), "")
        newValue = newValue.replace(",", ".")

        return newValue.toDouble()
    }

    fun returnIntPorcentFromString(value : String) : Int {
        return value.replace("[%]".toRegex(), "").toInt()
    }
}