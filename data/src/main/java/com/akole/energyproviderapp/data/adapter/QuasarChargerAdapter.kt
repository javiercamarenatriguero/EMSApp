package com.akole.energyproviderapp.data.adapter

import com.akole.energyproviderapp.data.entity.QuasarEnergyLiveData
import com.akole.energyproviderapp.data.utils.parseToHistoricalDataModel
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import kotlinx.coroutines.*
import java.util.*

class QuasarChargerAdapter {
    // Cached historical data during the session
    private val historicalDataList: MutableList<EnergyHistoricalData> = mutableListOf()

    // Cached total charged & discharged energy during the session
    private val totalQuasarChargedEnergy = 0.0f
    private val totalQuasarDischargedEnergy = 0.0f

    // Coroutine params
    private val scope = CoroutineScope(Dispatchers.IO)
    private var isServiceActive = false

    fun startLiveData() {
        if (!isServiceActive) {
            isServiceActive = true
            startEmulatedLiveDataProvider()
        }
    }

    fun stopLiveData() {
        isServiceActive = false
        scope.cancel()
    }

    fun getHistoricalDataList(): List<EnergyHistoricalData> {
        return historicalDataList.toList()
    }

    // This method emulates a live data provider loop as an external sensor
    private fun startEmulatedLiveDataProvider() {
        scope.launch {
            delay(CONNECTING_INITIAL_DELAY_MS)
            while (isActive && isServiceActive) {
                delay(LIVE_DATA_SAMPLING_RATE_MS)
                historicalDataList.add(
                    getMockQuasarEnergyLiveData().parseToHistoricalDataModel(Date().toString())
                )
            }
        }
    }

    companion object {
        // Coroutine timer
        private const val CONNECTING_INITIAL_DELAY_MS = 2000L
        private const val LIVE_DATA_SAMPLING_RATE_MS = 2000L
        // Emulated EMS params
        private const val SOLAR_POWER_BASE_VALUE = 30f
        private val SOLAR_POWER_DIFF_VALUES = (-20..20)
        private const val GRID_POWER_BASE_VALUE = 10f
        private val GRID_POWER_DIFF_VALUES = (-10..10)
        private const val BUILDING_POWER_BASE_VALUE = 40f
        private val BUILDING_POWER_DIFF_VALUES = (-20..20)

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
                timestamp = ""
            )
        }
    }
}
