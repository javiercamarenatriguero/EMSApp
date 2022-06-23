package com.akole.energyproviderapp.data.datasources

import com.akole.energyproviderapp.domain.datastores.EnergyHistoricalDataStore
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.usecases.GetHistoricalDataResponse
import com.akole.energyproviderapp.domain.usecases.SaveHistoricalDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalEnergyHistoricalDataDatastore @Inject constructor() : EnergyHistoricalDataStore {
    override suspend fun getHistoricalData(): Flow<GetHistoricalDataResponse> {
        return flowOf(GetHistoricalDataResponse.Loading)
    }

    override suspend fun saveHistoricalData(data: EnergyHistoricalData): Flow<SaveHistoricalDataResponse> {
        return flowOf(SaveHistoricalDataResponse.Success)
    }
}