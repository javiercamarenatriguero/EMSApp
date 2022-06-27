package com.akole.energyproviderapp.ui.utils

import com.akole.energyproviderapp.ui.screens.home.UiState

internal fun UiState.getGridPowerPercentageToBuilding() : Float {
    val result = 100 * (buildingDemandPower + quasarsPower - solarPower) / buildingDemandPower
    return if (result.equals(Float.NaN)) 0.0f else result
}

internal fun UiState.getSolarPowerPercentageToBuilding() : Float {
    val result = 100 * (buildingDemandPower + quasarsPower - gridPower) / buildingDemandPower
    return if (result.equals(Float.NaN)) 0.0f else result
}

internal fun UiState.getCarElectricPowerPercentageToBuilding() : Float {
    val result = if (quasarsPower < 0) 100 * kotlin.math.abs(quasarsPower) / buildingDemandPower else 0.0f
    return if (result.equals(Float.NaN)) 0.0f else result
}
