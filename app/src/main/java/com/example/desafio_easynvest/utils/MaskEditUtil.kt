package com.example.desafio_easynvest.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import java.text.NumberFormat

object MaskEditUtil {
    val FORMAT_PORCENT = "###%"
    val FORMAT_DATE = "##/##/####"
    var maskEdit = ""
    private var formated: String? = null
    private var current: String? = ""

    fun mask(ediTxt: EditText, mask: String, real : Boolean, secontadyEditTxt : EditText, terciaryEditText : EditText, btnToEnable : Button) : TextWatcher {

        maskEdit = ""

        return object : TextWatcher {
            var isUpdating: Boolean = false
            var old = ""

            private var mMask = mask

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (real) {
                     if (s.toString() != current) {
                            ediTxt.removeTextChangedListener(this)

                            val cleanString = s.toString().replace("[R$,.\u00A0]".toRegex(), "")
                            val parsed = cleanString.toDouble()
                            formated = NumberFormat.getCurrencyInstance().format(parsed / 100)
                            current = formated
                            formated = formated.toString().replace("\u00A0", "")
                            ediTxt.setText(formated.toString())
                            ediTxt.setSelection(formated!!.length)

                            ediTxt.addTextChangedListener(this)
                     }
                } else {
                    val str = unmask(s.toString())
                    var mascara = ""
                    if (isUpdating) {
                        old = str
                        isUpdating = false
                        return
                    }

                    if (maskEdit.isNotEmpty())
                        mMask = maskEdit

                    var i = 0
                    for (m in mMask.toCharArray()) {
                        if (m != '#' && str.length > old.length) {
                            mascara += m
                            continue
                        }
                        try {
                            mascara += str[i]
                        } catch (e: Exception) {
                            break
                        }

                        i++
                    }
                    isUpdating = true
                    ediTxt.setText(mascara)
                    ediTxt.setSelection(mascara.length)
                }

                btnToEnable.isEnabled = ediTxt.text.isNotEmpty() && secontadyEditTxt.text.isNotEmpty() && terciaryEditText.text.isNotEmpty()
            }
        }
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "").replace("[/]".toRegex(), "")
            .replace("[(]".toRegex(), "").replace(
                "[ ]".toRegex(), ""
            ).replace("[:]".toRegex(), "").replace("[)]".toRegex(), "").replace("[%]".toRegex(), "")
    }
}
