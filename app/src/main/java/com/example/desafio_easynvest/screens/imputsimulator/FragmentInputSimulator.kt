package com.example.desafio_easynvest.screens.imputsimulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_easynvest.R
import com.example.desafio_easynvest.api.repository.SimulatorRepository
import com.example.desafio_easynvest.model.ResponseSimulator
import com.example.desafio_easynvest.model.request.MyResquest
import com.example.desafio_easynvest.screens.imputsimulator.viewmodel.ImputSimulatorViewModel
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

        btnSimular.setOnClickListener {
            simulatorRepository.myResquest = setUpRequest()
            viewModel.simulate(simulatorRepository)
        }

        observeFields()
    }

    interface OnClickSimulate {
        fun onClickSimulate(responseSimulator: ResponseSimulator)
    }

    private fun setUpRequest() : MyResquest {
        return MyResquest(etValorAplicar.text.toString().toDouble(),
            etPercentualCDI.text.toString().toInt(), etDataVencimento.text.toString())
    }

    private fun observeFields() {
        viewModel.dadosJson.observe(this, androidx.lifecycle.Observer { dadosJson ->
            if (dadosJson != null) {
                if(dadosJson.responseSimulator != null) {
                    val onClick = activity as OnClickSimulate
                    onClick.onClickSimulate(dadosJson.responseSimulator!!)
                }
            }
        })
    }
}