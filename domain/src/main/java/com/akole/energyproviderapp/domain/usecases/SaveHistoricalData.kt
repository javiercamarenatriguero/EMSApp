package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EnergyHistoricalDataStore
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import kotlinx.coroutines.flow.Flow

class SaveHistoricalData(
    private val energyHistoricalDataStore: EnergyHistoricalDataStore
) {
    suspend operator fun invoke(data: EnergyHistoricalData): Flow<SaveHistoricalDataResponse> =
        energyHistoricalDataStore.saveHistoricalData(data)
}

sealed class SaveHistoricalDataResponse {
    object Loading: SaveHistoricalDataResponse()
    object Success: SaveHistoricalDataResponse()
    data class Error(val exception: Exception): SaveHistoricalDataResponse()
}