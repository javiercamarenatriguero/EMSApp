package com.akole.energyproviderapp.ui.utils

import com.akole.energyproviderapp.ui.screens.home.UiState
import com.akole.energyproviderapp.ui.utils.home.getCarElectricPowerPercentageToBuilding
import com.akole.energyproviderapp.ui.utils.home.getGridPowerPercentageToBuilding
import com.akole.energyproviderapp.ui.utils.home.getSolarPowerPercentageToBuilding
import org.junit.Assert
import org.junit.Test

class UiStateTest {

    @Test
    internal fun `CHECK Percentages values WHEN quasars power is positive`() {
        val uiState = UiState(
            quasarsPower = MOCK_QUASAR_POSITIVE_POWER,
            gridPower = MOCK_GRID_POWER,
            solarPower = MOCK_SOLAR_POWER,
            buildingDemandPower = MOCK_BUILDING_POWER
        )

        val solarPercentage = uiState.getSolarPowerPercentageToBuilding()
        val gridPercentage = uiState.getGridPowerPercentageToBuilding()
        val quasarPercentage = uiState.getCarElectricPowerPercentageToBuilding()
        // Verified values
        Assert.assertEquals(25.0f, gridPercentage)
        Assert.assertEquals(75.0f, solarPercentage)
        Assert.assertEquals(0.0f, quasarPercentage)
        Assert.assertEquals(100f,
            quasarPercentage
                    + gridPercentage
                    + solarPercentage
        )
    }

    @Test
    internal fun `CHECK Percentages values WHEN quasars power is negative`() {
        val uiState = UiState(
            quasarsPower = MOCK_QUASAR_NEGATIVE_POWER,
            gridPower = MOCK_GRID_POWER,
            solarPower = MOCK_SOLAR_POWER,
            buildingDemandPower = MOCK_BUILDING_POWER
        )

        val solarPercentage = uiState.getSolarPowerPercentageToBuilding()
        val gridPercentage = uiState.getGridPowerPercentageToBuilding()
        val quasarPercentage = uiState.getCarElectricPowerPercentageToBuilding()
        // Verified values
        Assert.assertEquals(50.0f, gridPercentage)
        Assert.assertEquals(37.5f, solarPercentage)
        Assert.assertEquals(12.5f, quasarPercentage)
        Assert.assertEquals(100f,
            quasarPercentage
                    + gridPercentage
                    + solarPercentage
        )
    }

    companion object {
        private const val MOCK_SOLAR_POWER = 50.0f
        private const val MOCK_GRID_POWER = 40.0f
        private const val MOCK_BUILDING_POWER = 80.0f
        private const val MOCK_QUASAR_POSITIVE_POWER = 20.0f
        private const val MOCK_QUASAR_NEGATIVE_POWER = -10.0f

    }
}