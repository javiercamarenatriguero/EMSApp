package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EMSDataStore
import kotlinx.coroutines.flow.Flow

class StopLiveDataConnection(
    private val EMSDataStore: EMSDataStore
) {
    suspend operator fun invoke(): Flow<StopLiveDataConnectionResponse> =
        EMSDataStore.stopLiveDataConnection()
}

sealed class StopLiveDataConnectionResponse {
    object Loading: StopLiveDataConnectionResponse()
    object Success: StopLiveDataConnectionResponse()
    data class Error(val exception: Exception): StopLiveDataConnectionResponse()
}