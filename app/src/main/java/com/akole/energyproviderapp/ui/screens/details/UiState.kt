package com.akole.energyproviderapp.ui.screens.details

import com.akole.energyproviderapp.domain.models.EnergyHistoricalData

data class UiState(
    val historicalDataList: List<EnergyHistoricalData> = emptyList(),
    val isLoading: Boolean = false
)