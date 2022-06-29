package com.akole.energyproviderapp.ui.utils.home

import com.akole.energyproviderapp.ui.screens.home.UiState

internal fun UiState.getGridPowerPercentageToBuilding() : Float {
    // According to the API, the car is charged JUST with grid power
    val powerToBuildingFromGrid = if (quasarsPower > 0) gridPower - quasarsPower else gridPower
    return getPercentage(
        sourcePower = powerToBuildingFromGrid,
        totalPower = buildingDemandPower
    )
}

internal fun UiState.getSolarPowerPercentageToBuilding() : Float {
    val powerToBuildingFromSolar = buildingDemandPower + quasarsPower - gridPower
    return getPercentage(
        sourcePower = powerToBuildingFromSolar,
        totalPower = buildingDemandPower
    )
}

internal fun UiState.getCarElectricPowerPercentageToBuilding() : Float {
    val powerToBuildingFromElectricCar = if (quasarsPower < 0) kotlin.math.abs(quasarsPower) else 0.0f
    return getPercentage(
        sourcePower = powerToBuildingFromElectricCar,
        totalPower = buildingDemandPower
    )
}

private fun getPercentage(sourcePower: Float, totalPower: Float): Float {
    val result = 100 * sourcePower / totalPower
    return if (result.equals(Float.NaN)) 0.0f else result
}

