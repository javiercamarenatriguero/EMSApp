package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EMSDataStore
import com.akole.energyproviderapp.domain.models.EnergyLiveData
import kotlinx.coroutines.flow.Flow

class StartLiveDataConnection(
    private val EMSDataStore: EMSDataStore
) {
    suspend operator fun invoke(): Flow<StartLiveDataConnectionResponse> =
        EMSDataStore.startLiveDataConnection()
}

sealed class StartLiveDataConnectionResponse {
    object Loading: StartLiveDataConnectionResponse()
    object Success: StartLiveDataConnectionResponse()
    data class OnData(val energyLiveData: EnergyLiveData): StartLiveDataConnectionResponse()
    data class Error(val exception: Exception): StartLiveDataConnectionResponse()
}