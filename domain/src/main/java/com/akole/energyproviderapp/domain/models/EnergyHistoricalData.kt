package com.akole.energyproviderapp.domain.models

data class EnergyHistoricalData (
    val solarActivePower: Float,
    val quasarsPower: Float,
    val gridActivePower: Float,
    val buildingActivePower: Float,
    val timestamp: Long
)