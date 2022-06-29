package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import com.akole.energyproviderapp.domain.models.EnergyLiveData
import kotlinx.coroutines.flow.Flow

class StartLiveDataConnection(
    private val emsDataSource: EMSDataSource
) {
    suspend operator fun invoke(): Flow<StartLiveDataConnectionResponse> =
        emsDataSource.startLiveDataConnection()
}

sealed class StartLiveDataConnectionResponse {
    object Loading: StartLiveDataConnectionResponse()
    object Success: StartLiveDataConnectionResponse()
    data class OnData(val energyLiveData: EnergyLiveData): StartLiveDataConnectionResponse()
    data class Error(val exception: Exception): StartLiveDataConnectionResponse()
}