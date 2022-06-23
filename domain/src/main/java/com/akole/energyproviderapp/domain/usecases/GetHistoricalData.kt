package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EnergyHistoricalDataStore
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import kotlinx.coroutines.flow.Flow

class GetHistoricalData(
    private val energyHistoricalDataStore: EnergyHistoricalDataStore
) {
    suspend operator fun invoke(): Flow<GetHistoricalDataResponse> =
        energyHistoricalDataStore.getHistoricalData()
}

sealed class GetHistoricalDataResponse {
    object Loading: GetHistoricalDataResponse()
    data class Success(val data: List<EnergyHistoricalData>): GetHistoricalDataResponse()
    data class Error(val exception: Exception): GetHistoricalDataResponse()
}