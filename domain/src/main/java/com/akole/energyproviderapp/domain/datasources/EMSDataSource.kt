package com.akole.energyproviderapp.domain.datasources

import com.akole.energyproviderapp.domain.usecases.*
import kotlinx.coroutines.flow.Flow

interface EMSDataSource {
    suspend fun startLiveDataConnection(): Flow<StartLiveDataConnectionResponse>
    suspend fun stopLiveDataConnection(): Flow<StopLiveDataConnectionResponse>
    suspend fun getHistoricalData(): Flow<GetHistoricalDataResponse>
}
