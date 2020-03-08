package com.example.desafio_easynvest.screens.simulatorresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafio_easynvest.R
import com.example.desafio_easynvest.model.DadosJson
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
            tvValAplicadoInicial.text = responseSimulator.investmentParameter!!.investedAmount.toString()
            tvValDataResgate.text = responseSimulator.investmentParameter!!.maturityDate
            tvValDiasCorridos.text = responseSimulator.investmentParameter!!.maturityTotalDays.toString()
            tvValPercentualCDI.text = responseSimulator.investmentParameter!!.rate.toString()

        }

        tvValBruto.text = responseSimulator.grossAmount.toString()
        tvRendimentoTotal.text = tvRendimentoTotal.text.toString().replace("__VALOR",responseSimulator.grossAmountProfit.toString())
        tvValResultado.text = responseSimulator.grossAmount.toString()
        tvValRendimento.text = responseSimulator.grossAmountProfit.toString()
        tvValIRRendimento.text = responseSimulator.taxesAmount.toString() + "[" + responseSimulator.taxesRate.toString() + "]"
        tvValLiquido.text = responseSimulator.netAmount.toString()
        tvValRendimentoMensal.text = responseSimulator.monthlyGrossRateProfit.toString()
        tvValRentabilidadeAnual.text = responseSimulator.monthlyGrossRateProfit.toString()
        tvValRentabilidadePeriodo.text = responseSimulator.rateProfit.toString()
    }
}