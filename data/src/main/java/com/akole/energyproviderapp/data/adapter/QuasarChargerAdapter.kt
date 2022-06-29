package com.akole.energyproviderapp.data.adapter

import com.akole.energyproviderapp.data.entity.QuasarEnergyLiveData
import com.akole.energyproviderapp.data.utils.getEnergyAmount
import com.akole.energyproviderapp.data.utils.parseToHistoricalDataModel
import com.akole.energyproviderapp.data.utils.parseToModel
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.models.EnergyLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.util.*

class QuasarChargerAdapter {
    // Cached historical data during the session
    private val historicalDataList: MutableList<EnergyHistoricalData> = mutableListOf()

    // Cached cumulative charged & discharged energy during the session
    private var quasarsTotalChargedEnergy = 0.0f
    private var quasarsTotalDischargedEnergy = 0.0f

    // Coroutine params
    private val scope = CoroutineScope(Dispatchers.IO)
    private var isServiceActive = false

    private val _liveData = Channel<EnergyLiveData>(Channel.BUFFERED)
    val liveData = _liveData.receiveAsFlow()

    fun startLiveData() {
        if (!isServiceActive) {
            isServiceActive = true
            startLiveDataProvider()
        }
    }

    fun stopLiveData() {
        isServiceActive = false
        resetSession()
    }

    fun getHistoricalDataList(): List<EnergyHistoricalData> {
        return historicalDataList.toList()
    }

    // This method emulates a live data provider loop as an external sensor
    private fun startLiveDataProvider() {
        scope.launch {
            while (isActive && isServiceActive) {
                delay(LIVE_DATA_SAMPLING_RATE_MS)
                val mockLiveData = getMockQuasarEnergyLiveData()
                emit(mockLiveData)
                saveOnCache(mockLiveData)
            }
        }
    }

    private suspend fun emit(value: QuasarEnergyLiveData) {
        val quasarsCurrentEnergy = calculateQuasarEnergy(value)
        updateQuasarsEnergyAmount(quasarsCurrentEnergy)
        _liveData.send(
            value.parseToModel(
                quasarsCurrentEnergy = kotlin.math.abs(quasarsCurrentEnergy),
                quasarsTotalChargedEnergy = quasarsTotalChargedEnergy,
                quasarsTotalDischargedEnergy = quasarsTotalDischargedEnergy
            )
        )
    }

    private fun updateQuasarsEnergyAmount(value: Float) =
        if(value > 0) {
            quasarsTotalChargedEnergy = quasarsTotalChargedEnergy.plus(kotlin.math.abs(value))
        } else {
            quasarsTotalDischargedEnergy = quasarsTotalDischargedEnergy.plus(kotlin.math.abs(value))
        }

    private fun saveOnCache(value: QuasarEnergyLiveData) {
        historicalDataList.add(
            value.parseToHistoricalDataModel()
        )
    }

    private fun calculateQuasarEnergy(value: QuasarEnergyLiveData) =
        value.quasarsPower.getEnergyAmount(LIVE_DATA_SAMPLING_RATE_MS)

    private fun resetSession() {
        quasarsTotalChargedEnergy = 0.0f
        quasarsTotalDischargedEnergy = 0.0f
        historicalDataList.clear()
    }


    companion object {
        // Coroutine timer
        private const val LIVE_DATA_SAMPLING_RATE_MS = 1000L
        // Emulated EMS params (random values)
        private const val SOLAR_POWER_BASE_VALUE = 30f
        private val SOLAR_POWER_DIFF_VALUES = (-10..10)
        private const val GRID_POWER_BASE_VALUE = 20f
        private val GRID_POWER_DIFF_VALUES = (-10..10)
        private const val BUILDING_POWER_BASE_VALUE = 60f
        private val BUILDING_POWER_DIFF_VALUES = (-10..10)

        // Generate Mock Live data coming from Quasar Charger as defined on API
        fun getMockQuasarEnergyLiveData(): QuasarEnergyLiveData {
            val solarPower =
                SOLAR_POWER_BASE_VALUE + (SOLAR_POWER_DIFF_VALUES).random().toFloat()
            val gridPower =
                GRID_POWER_BASE_VALUE + (GRID_POWER_DIFF_VALUES).random().toFloat()
            val buildingPowerDemand =
                BUILDING_POWER_BASE_VALUE + (BUILDING_POWER_DIFF_VALUES).random().toFloat()
            val quasarsPower = solarPower + gridPower - buildingPowerDemand
            return QuasarEnergyLiveData(
                solarPower = solarPower,
                gridPower = gridPower,
                buildingPowerDemand = buildingPowerDemand,
                quasarsPower = quasarsPower,
                timestamp = Date().time
            )
        }
    }
}
