package com.example.desafio_easynvest.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.desafio_easynvest.R
import com.example.desafio_easynvest.model.ResponseSimulator
import com.example.desafio_easynvest.screens.imputsimulator.FragmentInputSimulator
import com.example.desafio_easynvest.screens.simulatorresult.FragmentSimulatorResult
import com.example.desafio_easynvest.utils.Constants

class MainActivity : AppCompatActivity(), FragmentSimulatorResult.OnClickSimulateAgain, FragmentInputSimulator.OnClickSimulate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, FragmentInputSimulator(), Constants.TAG_IMPUT_SIMULATOR)
            .disallowAddToBackStack()
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeFragments(){
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }

    private fun openImputSimulator() {
        removeFragments()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, FragmentInputSimulator(), Constants.TAG_IMPUT_SIMULATOR)
            .disallowAddToBackStack()
            .setCustomAnimations(R.anim.slide_to_left,R.anim.slide_from_right)
            .commit()
    }

    private fun openSimulatorResult(responseSimulator: ResponseSimulator) {
        val bundle = Bundle()
        val fragment = FragmentSimulatorResult()
        removeFragments()

        bundle.putSerializable("response",responseSimulator)
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, fragment, Constants.TAG_SIMULATOR_RESULT)
            .disallowAddToBackStack()
            .setCustomAnimations(R.anim.slide_from_right,R.anim.slide_to_left)
            .commit()
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.frame)) {
            is FragmentInputSimulator -> finish()
            is FragmentSimulatorResult -> openImputSimulator()
        }
    }

    override fun onClickSimulateAgain() {
        openImputSimulator()
    }

    override fun onClickSimulate(responseSimulator: ResponseSimulator) {
        openSimulatorResult(responseSimulator)
    }
}
