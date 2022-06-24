package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.datastores.EMSDataStore
import com.akole.energyproviderapp.domain.datastores.EMSListener
import kotlinx.coroutines.flow.Flow

class StartLiveDataConnection(
    private val EMSDataStore: EMSDataStore
) {
    suspend operator fun invoke(
        listener: EMSListener
    ): Flow<StartLiveDataConnectionResponse> =
        EMSDataStore.startLiveDataConnection(listener)
}

sealed class StartLiveDataConnectionResponse {
    object Loading: StartLiveDataConnectionResponse()
    object Success: StartLiveDataConnectionResponse()
    data class Error(val exception: Exception): StartLiveDataConnectionResponse()
}