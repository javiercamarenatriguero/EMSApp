package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import kotlinx.coroutines.flow.Flow

class StopLiveDataConnection(
    private val EMSDataSource: EMSDataSource
) {
    suspend operator fun invoke(): Flow<StopLiveDataConnectionResponse> =
        EMSDataSource.stopLiveDataConnection()
}

sealed class StopLiveDataConnectionResponse {
    object Loading: StopLiveDataConnectionResponse()
    object Success: StopLiveDataConnectionResponse()
    data class Error(val exception: Exception): StopLiveDataConnectionResponse()
}