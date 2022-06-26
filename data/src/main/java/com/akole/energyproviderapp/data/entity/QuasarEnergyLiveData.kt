package com.akole.energyproviderapp.data.entity

data class QuasarEnergyLiveData (
    val solarPower: Float,
    val quasarsPower: Float,
    val gridPower: Float,
    val buildingPowerDemand: Float,
    val timestamp: String
)