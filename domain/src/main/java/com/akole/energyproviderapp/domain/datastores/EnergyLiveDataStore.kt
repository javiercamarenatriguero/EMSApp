package com.akole.energyproviderapp.domain.datastores

import com.akole.energyproviderapp.domain.usecases.StartEnergyLiveDataProviderResponse
import com.akole.energyproviderapp.domain.usecases.StopEnergyLiveDataProviderResponse
import kotlinx.coroutines.flow.Flow

interface EnergyLiveDataStore {
    suspend fun startService(
        listener: EnergyProviderListener
    ): Flow<StartEnergyLiveDataProviderResponse>
    suspend fun stopService(): Flow<StopEnergyLiveDataProviderResponse>
}

