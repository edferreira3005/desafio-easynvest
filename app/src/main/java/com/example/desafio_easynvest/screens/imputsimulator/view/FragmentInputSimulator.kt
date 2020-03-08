package com.example.desafio_easynvest.screens.imputsimulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_easynvest.R
import com.example.desafio_easynvest.api.repository.SimulatorRepository
import com.example.desafio_easynvest.model.DadosJson
import com.example.desafio_easynvest.model.request.MyResquest
import com.example.desafio_easynvest.screens.imputsimulator.viewmodel.ImputSimulatorViewModel
import com.example.desafio_easynvest.utils.MaskEditUtil
import com.example.desafio_easynvest.utils.Util
import com.example.desafio_easynvest.utils.exception.SimulatorException
import kotlinx.android.synthetic.main.layout_fragment_imput_simulator.*
import org.koin.android.ext.android.inject

class FragmentInputSimulator : Fragment() {
    private lateinit var viewModel: ImputSimulatorViewModel
    private val simulatorRepository : SimulatorRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_imput_simulator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get<ImputSimulatorViewModel>(ImputSimulatorViewModel::class.java)

        etValorAplicar.requestFocus()
        etDataVencimento.addTextChangedListener(MaskEditUtil.mask(etDataVencimento, MaskEditUtil.FORMAT_DATE,false, etValorAplicar, etPercentualCDI, btnSimular))
        etValorAplicar.addTextChangedListener(MaskEditUtil.mask(etValorAplicar,"",true,etDataVencimento, etPercentualCDI, btnSimular))
        etPercentualCDI.addTextChangedListener(MaskEditUtil.mask(etPercentualCDI,MaskEditUtil.FORMAT_PORCENT,false,etValorAplicar, etDataVencimento, btnSimular))

        btnSimular.setOnClickListener {
            if(validateFields()) {
                simulatorRepository.myResquest = setUpRequest()
                viewModel.simulate(simulatorRepository)
            }
        }

        observeFields()
    }

    interface OnClickSimulate {
        fun onClickSimulate(responseSimulator: DadosJson)
    }

    private fun setUpRequest() : MyResquest {
        return MyResquest(
            Util.returnDoubleRealFromString(etValorAplicar.text.toString()),
            Util.returnIntPorcentFromString(etPercentualCDI.text.toString()),
            Util.returnFormatedDate(etDataVencimento.text.toString(),resources.getString(R.string.format_request), resources.getString(R.string.format_screen)))
    }

    private fun observeFields() {
        viewModel.dadosJson.observe(this, androidx.lifecycle.Observer { dadosJson ->
            if (dadosJson != null) {
                val onClick = activity as OnClickSimulate
                onClick.onClickSimulate(dadosJson)
            }
        })

        viewModel.loading.observe(this, androidx.lifecycle.Observer { loading ->
            if (loading) {
                btnSimular.visibility = View.INVISIBLE
                progress.visibility = View.VISIBLE
                etDataVencimento.isEnabled = false
                etPercentualCDI.isEnabled = false
                etValorAplicar.isEnabled = false
            }else {
                btnSimular.visibility = View.VISIBLE
                progress.visibility = View.GONE
                etDataVencimento.isEnabled = true
                etPercentualCDI.isEnabled = true
                etValorAplicar.isEnabled = true
            }
        })

        viewModel.exception.observe(this, androidx.lifecycle.Observer { exception ->
            SimulatorException(exception,context!!).showError()
        })
    }

    private fun validateFields() : Boolean {
        var validated = true

        if(!Util.validateNumberValue(etValorAplicar, true, "",0))
            validated = false

        if(!Util.validateNumberValue(etPercentualCDI, false, "%", resources.getInteger(R.integer.min_cdi)))
            validated = false

        if(!Util.validateDateValue(etDataVencimento))
            validated = false

        return validated
    }
}