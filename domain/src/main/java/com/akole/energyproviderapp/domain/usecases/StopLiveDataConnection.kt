package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import kotlinx.coroutines.flow.Flow

class StopLiveDataConnection(
    private val emsDataSource: EMSDataSource
) {
    suspend operator fun invoke(): Flow<StopLiveDataConnectionResponse> =
        emsDataSource.stopLiveDataConnection()
}

sealed class StopLiveDataConnectionResponse {
    object Loading: StopLiveDataConnectionResponse()
    object Success: StopLiveDataConnectionResponse()
    data class Error(val exception: Exception): StopLiveDataConnectionResponse()
}