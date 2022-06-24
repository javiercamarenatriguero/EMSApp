package com.akole.energyproviderapp.data.datasources

import com.akole.energyproviderapp.domain.datastores.EMSDataStore
import com.akole.energyproviderapp.domain.models.EnergyLiveData
import com.akole.energyproviderapp.domain.usecases.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class QuasarChargerDataStore @Inject constructor() : EMSDataStore {
    override suspend fun startLiveDataConnection(): Flow<StartLiveDataConnectionResponse> {
        return flow {
            emit(StartLiveDataConnectionResponse.Loading)
            delay(5000)
            emit(StartLiveDataConnectionResponse.OnData(
                EnergyLiveData(
                    solarPower = 0.0f,
                    quasarsPower = 0.0f,
                    gridPower = 0.0f,
                    buildingPowerDemand = 0.0f,
                    quasarTotalChargedEnergy = 0.0f,
                    quasarTotalDischargedEnergy = 0.0f,
                    quasarCurrentEnergy = 0.0f
                ))
            )
        }
    }

    override suspend fun stopLiveDataConnection(): Flow<StopLiveDataConnectionResponse> {
        return flowOf(StopLiveDataConnectionResponse.Loading)
    }

    override suspend fun getHistoricalData(): Flow<GetHistoricalDataResponse> {
        return flowOf(GetHistoricalDataResponse.Loading)
    }
}