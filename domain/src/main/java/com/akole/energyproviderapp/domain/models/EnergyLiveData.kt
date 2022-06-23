package com.akole.energyproviderapp.domain.models

data class EnergyLiveData (
    val solarPower: Float,
    val quasarsPower: Float,
    val gridPower: Float,
    val buildingPowerDemand: Float,
    val quasarChargedEnergy: Float,
    val quasarDischargedEnergy: Float
    )