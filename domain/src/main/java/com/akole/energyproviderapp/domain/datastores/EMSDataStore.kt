package com.akole.energyproviderapp.domain.datastores

import com.akole.energyproviderapp.domain.usecases.*
import kotlinx.coroutines.flow.Flow

interface EMSDataStore {
    suspend fun startLiveDataConnection(): Flow<StartLiveDataConnectionResponse>
    suspend fun stopLiveDataConnection(): Flow<StopLiveDataConnectionResponse>
    suspend fun getHistoricalData(): Flow<GetHistoricalDataResponse>
}
