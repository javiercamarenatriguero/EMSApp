package com.akole.energyproviderapp.data.utils

import com.akole.energyproviderapp.data.entity.QuasarEnergyLiveData
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.models.EnergyLiveData

internal fun Float.getEnergyAmount(timeDiffMilliseconds: Long): Float =
    // Power (kW) * Time gap between samples (Hours)
    this * ((timeDiffMilliseconds.toFloat() / MILLISECONDS_INTO_HOUR))

private const val MILLISECONDS_INTO_HOUR = 3_600_000

internal fun QuasarEnergyLiveData.parseToModel(
    quasarsCurrentEnergy: Float,
    quasarsTotalChargedEnergy: Float,
    quasarsTotalDischargedEnergy: Float
): EnergyLiveData =
    EnergyLiveData(
        solarPower = solarPower,
        gridPower = gridPower,
        buildingPowerDemand = buildingPowerDemand,
        quasarsPower = quasarsPower,
        quasarsCurrentEnergy = quasarsCurrentEnergy,
        quasarsTotalChargedEnergy = quasarsTotalChargedEnergy,
        quasarsTotalDischargedEnergy = quasarsTotalDischargedEnergy
    )

internal fun QuasarEnergyLiveData.parseToHistoricalDataModel(): EnergyHistoricalData =
    EnergyHistoricalData(
        solarActivePower = solarPower,
        gridActivePower = gridPower,
        buildingActivePower = buildingPowerDemand,
        quasarsPower = quasarsPower,
        timestamp = timestamp
    )
