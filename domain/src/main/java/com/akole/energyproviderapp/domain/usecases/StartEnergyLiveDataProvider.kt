package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EnergyLiveDataStore
import com.akole.energyproviderapp.domain.datastores.EnergyProviderListener
import kotlinx.coroutines.flow.Flow

class StartEnergyLiveDataProvider(
    private val energyLiveDataStore: EnergyLiveDataStore
) {
    suspend operator fun invoke(
        listener: EnergyProviderListener
    ): Flow<StartEnergyLiveDataProviderResponse> =
        energyLiveDataStore.startService(listener)
}

sealed class StartEnergyLiveDataProviderResponse {
    object Loading: StartEnergyLiveDataProviderResponse()
    object Success: StartEnergyLiveDataProviderResponse()
    data class Error(val exception: Exception): StartEnergyLiveDataProviderResponse()
}