package com.example.desafio_easynvest.screens.simulatorresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafio_easynvest.R
import kotlinx.android.synthetic.main.layout_fragment_simulator_result.*

class FragmentSimulatorResult : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_simulator_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSumularNovamente.setOnClickListener {
            val onClick = activity as OnClickSimulateAgain
            onClick.onClickSimulateAgain()
        }
    }

    interface OnClickSimulateAgain {
        fun onClickSimulateAgain()
    }
}