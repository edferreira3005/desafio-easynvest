package com.example.desafio_easynvest.screens.simulatorresult.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafio_easynvest.R
import com.example.desafio_easynvest.model.DadosJson
import com.example.desafio_easynvest.utils.Util
import kotlinx.android.synthetic.main.layout_fragment_simulator_result.*

class FragmentSimulatorResult : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_simulator_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var responseSimulator: DadosJson? = null
        if(arguments != null)
            responseSimulator = arguments!!.getSerializable("response") as DadosJson?

        if(responseSimulator != null)
            setFields(responseSimulator)

        btnSumularNovamente.setOnClickListener {
            val onClick = activity as OnClickSimulateAgain
            onClick.onClickSimulateAgain()
        }
    }

    interface OnClickSimulateAgain {
        fun onClickSimulateAgain()
    }

    private fun setFields(responseSimulator: DadosJson) {

        if(responseSimulator.investmentParameter != null) {
            tvValAplicadoInicial.text = Util.returnReaisValue(responseSimulator.investmentParameter!!.investedAmount!!)
            tvValDataResgate.text = Util.returnFormatedDate(responseSimulator.investmentParameter!!.maturityDate!!,resources.getString(R.string.format_screen),
                "yyyy-MM-dd'T'HH:mm:ss")
            tvValDiasCorridos.text = responseSimulator.investmentParameter!!.maturityTotalDays.toString()
            tvValPercentualCDI.text = Util.returnPorcentage(responseSimulator.investmentParameter!!.rate!!,true)

        }

        tvValBruto.text = Util.returnReaisValue(responseSimulator.grossAmount!!)
        tvRendimentoTotal.text = Html.fromHtml(tvRendimentoTotal.text.toString() //deprecado, mas o teste fala para min sdk 16
            .replace("__VALOR","<font color='#01C8B4'>" + Util.returnReaisValue(responseSimulator.grossAmountProfit!!) + "</font>"))
        tvValResultado.text = Util.returnReaisValue(responseSimulator.grossAmount!!)
        tvValRendimento.text = Util.returnReaisValue(responseSimulator.grossAmountProfit!!)
        tvValIRRendimento.text = Util.returnReaisValue(responseSimulator.taxesAmount!!) + "[" + Util.returnPorcentage(responseSimulator.taxesRate!!,false) + "]"
        tvValLiquido.text = Util.returnReaisValue(responseSimulator.netAmount!!)
        tvValRendimentoMensal.text = Util.returnPorcentage(responseSimulator.monthlyGrossRateProfit!!,false)
        tvValRentabilidadeAnual.text = Util.returnPorcentage(responseSimulator.monthlyGrossRateProfit!!,false)
        tvValRentabilidadePeriodo.text = Util.returnPorcentage(responseSimulator.rateProfit!!,false)
    }
}