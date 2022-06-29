package com.akole.energyproviderapp.data.utils

import com.akole.energyproviderapp.data.entity.QuasarEnergyLiveData
import org.junit.Assert
import org.junit.Test

class QuasarChargerDataSourceExtTest {

    @Test
    internal fun `GIVEM a QuasarEnergyLiveData instance WHEN parseToModel is called THEN returns EnergyLiveData instance`() {
        val parseEnergyLiveData = MOCK_QUASAR_LIVE_DATA.parseToModel(
            quasarsCurrentEnergy = MOCK_QUASAR_CURRENT_ENERGY,
            quasarsTotalChargedEnergy = MOCK_QUASAR_CHARGED_ENERGY,
            quasarsTotalDischargedEnergy = MOCK_QUASAR_DISCHARGED_ENERGY
        )
        Assert.assertEquals(MOCK_SOLAR_POWER, parseEnergyLiveData.solarPower)
        Assert.assertEquals(MOCK_GRID_POWER, parseEnergyLiveData.gridPower)
        Assert.assertEquals(MOCK_BUILDING_POWER, parseEnergyLiveData.buildingPowerDemand)
        Assert.assertEquals(MOCK_QUASAR_POWER, parseEnergyLiveData.quasarsPower)
        Assert.assertEquals(MOCK_QUASAR_CURRENT_ENERGY, parseEnergyLiveData.quasarsCurrentEnergy)
        Assert.assertEquals(MOCK_QUASAR_CHARGED_ENERGY, parseEnergyLiveData.quasarsTotalChargedEnergy)
        Assert.assertEquals(MOCK_QUASAR_DISCHARGED_ENERGY, parseEnergyLiveData.quasarsTotalDischargedEnergy)
    }

    @Test
    internal fun `GIVEM a Power value AND time gap WHEN getEnergyAmount is called THEN returns Energy value`() {
        // Verified value
        Assert.assertEquals(0.0055555557f, MOCK_GRID_POWER.getEnergyAmount(MOCK_TIME_GAP))
        Assert.assertEquals(2.7777778E-4f, MOCK_SOLAR_POWER.getEnergyAmount(MOCK_TIME_GAP))
        Assert.assertEquals(0.008333334f, MOCK_BUILDING_POWER.getEnergyAmount(MOCK_TIME_GAP))
        Assert.assertEquals(0.011111111f, MOCK_QUASAR_POWER.getEnergyAmount(MOCK_TIME_GAP))
        Assert.assertEquals(0.0f, 0.0f.getEnergyAmount(MOCK_TIME_GAP))
    }

    @Test
    internal fun `GIVEM a QuasarEnergyLiveData instance WHEN parseToHistoricalDataModel is called THEN returns EnergyHistoricalData instance`() {
        val parseEnergyHistoricalData = MOCK_QUASAR_LIVE_DATA.parseToHistoricalDataModel()
        Assert.assertEquals(MOCK_SOLAR_POWER, parseEnergyHistoricalData.solarActivePower)
        Assert.assertEquals(MOCK_GRID_POWER, parseEnergyHistoricalData.gridActivePower)
        Assert.assertEquals(MOCK_BUILDING_POWER, parseEnergyHistoricalData.buildingActivePower)
        Assert.assertEquals(MOCK_QUASAR_POWER, parseEnergyHistoricalData.quasarsPower)
        Assert.assertEquals(MOCK_TIMESTAMP, parseEnergyHistoricalData.timestamp)
    }

    companion object {
        private const val MOCK_SOLAR_POWER = 1.0f
        private const val MOCK_GRID_POWER = 20.0f
        private const val MOCK_BUILDING_POWER = 30.0f
        private const val MOCK_QUASAR_POWER = 40.0f
        private const val MOCK_TIMESTAMP = 1_000_000L
        // Energy values
        private const val MOCK_QUASAR_CURRENT_ENERGY = 2.0f
        private const val MOCK_QUASAR_CHARGED_ENERGY = 10.0f
        private const val MOCK_QUASAR_DISCHARGED_ENERGY = 20.0f
        // Time gap between samples
        private const val MOCK_TIME_GAP = 1_000L

        private val MOCK_QUASAR_LIVE_DATA = QuasarEnergyLiveData(
            solarPower = MOCK_SOLAR_POWER,
            gridPower = MOCK_GRID_POWER,
            quasarsPower = MOCK_QUASAR_POWER,
            buildingPowerDemand = MOCK_BUILDING_POWER,
            timestamp = MOCK_TIMESTAMP
        )
    }
}