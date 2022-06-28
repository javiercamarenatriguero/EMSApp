package com.akole.energyproviderapp.data.datasources

import com.akole.energyproviderapp.data.adapter.QuasarChargerAdapter
import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import com.akole.energyproviderapp.domain.usecases.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class QuasarChargerDataSource @Inject constructor(
    private val adapter: QuasarChargerAdapter
) : EMSDataSource {
    override suspend fun startLiveDataConnection(): Flow<StartLiveDataConnectionResponse> {
        return flow {
            emit(StartLiveDataConnectionResponse.Loading)
            delay(SIMULATED_ADAPTER_CONNECTION_DELAY)
            emit(StartLiveDataConnectionResponse.Success)
            adapter.startLiveData()
            adapter.liveData.collect { liveData ->
                emit(StartLiveDataConnectionResponse.OnData(liveData))
            }
        }
    }

    override suspend fun stopLiveDataConnection(): Flow<StopLiveDataConnectionResponse> {
        return flow {
            adapter.stopLiveData()
            emit(StopLiveDataConnectionResponse.Success)
        }
    }

    override suspend fun getHistoricalData(): Flow<GetHistoricalDataResponse> {
        return flowOf(GetHistoricalDataResponse.Success(adapter.getHistoricalDataList()))
    }

    companion object {
        // Added delay in order to simulated a connecting delay with the adapter
        private const val SIMULATED_ADAPTER_CONNECTION_DELAY = 2000L
    }
}