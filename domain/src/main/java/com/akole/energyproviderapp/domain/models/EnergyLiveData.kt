package com.akole.energyproviderapp.domain.models

data class EnergyLiveData (
    val solarPower: Float,
    val quasarsPower: Float,
    val gridPower: Float,
    val buildingPowerDemand: Float,
    val quasarsTotalChargedEnergy: Float,
    val quasarsTotalDischargedEnergy: Float,
    val quasarsCurrentEnergy: Float
)