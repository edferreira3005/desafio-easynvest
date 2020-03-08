package com.example.desafio_easynvest.screens.imputsimulator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_easynvest.api.repository.SimulatorRepository
import com.example.desafio_easynvest.model.DadosJson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class ImputSimulatorViewModel : ViewModel() {

    private var simulatorRepository : SimulatorRepository? = null
    private val disposable = CompositeDisposable()
    var dadosJson: MutableLiveData<DadosJson> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()
    var isError : MutableLiveData<Boolean> = MutableLiveData()

    fun simulate(simulatorRepository: SimulatorRepository) {
        this.simulatorRepository = simulatorRepository

        simulateValues()
    }

    private fun simulateValues() {
        loading.value = true
        isError.value = false

        disposable.add(
            simulatorRepository!!.getInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DadosJson>() {
                    override fun onSuccess(dados: DadosJson) {
                        dadosJson.value = dados
                        loading.value = false
                    }
                    override fun onError(e: Throwable) {
                        loading.value = false
                        isError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}