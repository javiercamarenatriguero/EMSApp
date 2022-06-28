package com.akole.energyproviderapp.ui.screens.home

data class UiState(
    val quasarsCurrentEnergy: Float = 0.0f,
    val totalQuasarsChargedEnergy: Float = 0.0f,
    val totalQuasarsDischargedEnergy: Float = 0.0f,
    val quasarsPower: Float = 0.0f,
    val buildingDemandPower: Float = 0.0f,
    val solarPower: Float = 0.0f,
    val gridPower: Float = 0.0f,
    val isConnectionStopped: Boolean = false,
    val isConnectionLoading: Boolean = false,
    val isConnectionError: Boolean = false
)