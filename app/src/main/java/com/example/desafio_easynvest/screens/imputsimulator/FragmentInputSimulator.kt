package com.example.desafio_easynvest.screens.imputsimulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafio_easynvest.R
import kotlinx.android.synthetic.main.layout_fragment_imput_simulator.*

class FragmentInputSimulator : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_imput_simulator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSimular.setOnClickListener {
            val onClick = activity as OnClickSimulate
            onClick.onClickSimulate()
        }
    }

    interface OnClickSimulate {
        fun onClickSimulate()
    }
}