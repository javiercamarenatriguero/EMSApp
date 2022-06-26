package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import kotlinx.coroutines.flow.Flow

class GetHistoricalData(
    private val emsDataSource: EMSDataSource
) {
    suspend operator fun invoke(): Flow<GetHistoricalDataResponse> =
        emsDataSource.getHistoricalData()
}

sealed class GetHistoricalDataResponse {
    object Loading: GetHistoricalDataResponse()
    data class Success(val data: List<EnergyHistoricalData>): GetHistoricalDataResponse()
    data class Error(val exception: Exception): GetHistoricalDataResponse()
}