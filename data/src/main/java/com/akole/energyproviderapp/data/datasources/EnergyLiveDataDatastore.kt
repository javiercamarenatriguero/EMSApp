package com.akole.energyproviderapp.data.datasources

import com.akole.energyproviderapp.domain.datastores.EnergyLiveDataStore
import com.akole.energyproviderapp.domain.datastores.EnergyProviderListener
import com.akole.energyproviderapp.domain.usecases.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SensorLiveDataStore @Inject constructor() : EnergyLiveDataStore {
    override suspend fun startService(listener: EnergyProviderListener): Flow<StartEnergyLiveDataProviderResponse> {
        return flowOf(StartEnergyLiveDataProviderResponse.Loading)
    }

    override suspend fun stopService(): Flow<StopEnergyLiveDataProviderResponse> {
        return flowOf(StopEnergyLiveDataProviderResponse.Loading)
    }
}