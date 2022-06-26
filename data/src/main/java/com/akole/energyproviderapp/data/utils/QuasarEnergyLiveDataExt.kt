package com.akole.energyproviderapp.data.utils

import com.akole.energyproviderapp.data.entity.QuasarEnergyLiveData
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.models.EnergyLiveData

internal fun getEnergyAmount(power: Float, timeDiffMilliseconds: Long): Float {
    // Power (kW) * Time gap (Hours)
    return power * timeDiffMilliseconds * MILLISECONDS_TO_HOURS_FACTOR
}

private const val MILLISECONDS_TO_HOURS_FACTOR = 1 / 3_600_000

internal fun QuasarEnergyLiveData.parseToModel(): EnergyLiveData =
    EnergyLiveData(
        solarPower = solarPower,
        gridPower = gridPower,
        buildingPowerDemand = buildingPowerDemand,
        quasarsPower = quasarsPower,
        quasarCurrentEnergy = getEnergyAmount(power = quasarsPower, 5000),
        quasarTotalChargedEnergy = 0.0f,
        quasarTotalDischargedEnergy = 0.0f
    )

internal fun QuasarEnergyLiveData.parseToHistoricalDataModel(timestamp: String): EnergyHistoricalData =
    EnergyHistoricalData(
        solarActivePower = solarPower,
        gridActivePower = gridPower,
        buildingActivePower = buildingPowerDemand,
        quasarsPower = quasarsPower,
        timestamp = timestamp
    )
