package com.akole.energyproviderapp.ui.utils

import com.akole.energyproviderapp.ui.screens.home.UiState

internal fun UiState.getGridPowerPercentageToBuilding() : Float {
    // According to the API, the car is charged JUST with grid power
    val powerToBuildingFromGrid = if (quasarsPower > 0) gridPower - quasarsPower else gridPower
    val result = 100 * powerToBuildingFromGrid / buildingDemandPower
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
