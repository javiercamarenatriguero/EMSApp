package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EnergyLiveDataStore
import kotlinx.coroutines.flow.Flow

class StopEnergyLiveDataProvider(
    private val energyLiveDataStore: EnergyLiveDataStore
) {
    suspend operator fun invoke(): Flow<StopEnergyLiveDataProviderResponse> =
        energyLiveDataStore.stopService()
}

sealed class StopEnergyLiveDataProviderResponse {
    object Loading: StopEnergyLiveDataProviderResponse()
    object Success: StopEnergyLiveDataProviderResponse()
    data class Error(val exception: Exception): StopEnergyLiveDataProviderResponse()
}