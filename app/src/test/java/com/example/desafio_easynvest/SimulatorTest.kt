package com.example.desafio_easynvest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_easynvest.api.repository.SimulatorRepository
import com.example.desafio_easynvest.model.DadosJson
import com.example.desafio_easynvest.model.InvestmentParameter
import com.example.desafio_easynvest.screens.imputsimulator.viewmodel.ImputSimulatorViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor

class SimulatorTest {
    @Rule @JvmField
    var rule = InstantTaskExecutorRule()

    @Mock
    var repository: SimulatorRepository? = null

    @InjectMocks
    var viewModel: ImputSimulatorViewModel = ImputSimulatorViewModel()

    private var testSingle: Single<DadosJson>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDadosSuccess() {

        val dados = DadosJson()
        val invested = InvestmentParameter()
        dados.investmentParameter = invested

        testSingle = Single.just<DadosJson>(dados)
        Mockito.`when`<Any>(repository!!.getInfo()).thenReturn(testSingle)
        viewModel.simulate(repository!!)

        Assert.assertNotNull(viewModel.dadosJson.value)
        Assert.assertNotNull(viewModel.dadosJson.value!!.investmentParameter)
        Assert.assertEquals(false, viewModel.isError.value)
        Assert.assertEquals(false, viewModel.loading.value)
    }

    @Test
    fun getDadosFail() {
        testSingle = Single.error(Throwable())

        Mockito.`when`<Any>(repository!!.getInfo()).thenReturn(testSingle)
        viewModel.simulate(repository!!)

        Assert.assertEquals(true, viewModel.isError.value)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate: Scheduler = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorWorker(
                    Executor { runnable: Runnable -> runnable.run() },
                    true
                )
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
    }
}