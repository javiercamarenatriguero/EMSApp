package com.akole.energyproviderapp.domain.datastores

import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.usecases.GetHistoricalDataResponse
import com.akole.energyproviderapp.domain.usecases.SaveHistoricalDataResponse
import kotlinx.coroutines.flow.Flow

interface EnergyHistoricalDataStore {
    suspend fun getHistoricalData(): Flow<GetHistoricalDataResponse>
    suspend fun saveHistoricalData(data: EnergyHistoricalData): Flow<SaveHistoricalDataResponse>
}