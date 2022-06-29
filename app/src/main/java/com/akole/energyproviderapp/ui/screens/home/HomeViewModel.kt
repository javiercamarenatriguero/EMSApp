package com.akole.energyproviderapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnectionResponse
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnectionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val startLiveDataConnection: StartLiveDataConnection,
    private val stopLiveDataConnection: StopLiveDataConnection
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

    init {
        updateState(
            connectionState = ConnectionUiState.DISCONNECTED
        )
    }

    private fun startLiveDataService() {
        viewModelScope.launch {
            startLiveDataConnection().collectLatest { response ->
                when (response) {
                    is StartLiveDataConnectionResponse.Success -> {
                        updateState(
                            connectionState = ConnectionUiState.CONNECTED
                        )
                    }
                    is StartLiveDataConnectionResponse.Loading -> {
                        updateState(
                            connectionState = ConnectionUiState.CONNECTING
                        )
                    }
                    is StartLiveDataConnectionResponse.OnData -> {
                        updateState(
                            quasarsCurrentEnergy = response.energyLiveData.quasarsCurrentEnergy,
                            totalQuasarsChargedEnergy = response.energyLiveData.quasarsTotalChargedEnergy,
                            totalQuasarsDischargedEnergy = response.energyLiveData.quasarsTotalDischargedEnergy,
                            quasarsPower = response.energyLiveData.quasarsPower,
                            buildingDemandPower = response.energyLiveData.buildingPowerDemand,
                            solarPower = response.energyLiveData.solarPower,
                            gridPower = response.energyLiveData.gridPower
                        )
                    }
                    is StartLiveDataConnectionResponse.Error -> {
                        updateState(
                            connectionState = ConnectionUiState.DISCONNECTED
                        )
                    }
                }
            }
        }
    }

    private fun stopLiveDataService() {
        viewModelScope.launch {
            stopLiveDataConnection().collectLatest { response ->
                when (response) {
                    is StopLiveDataConnectionResponse.Success ->
                        updateState(
                            connectionState = ConnectionUiState.DISCONNECTED
                        )
                    is StopLiveDataConnectionResponse.Error ->
                        updateState(
                            connectionState = ConnectionUiState.DISCONNECTED
                        )
                    is StopLiveDataConnectionResponse.Loading ->
                        updateState(
                            connectionState = ConnectionUiState.CONNECTED
                        )
                }
            }
        }
    }

    fun on(event: ViewEvent): Unit = with(event) {
        when (this) {
            ViewEvent.SeeDetailsClicked -> onSeeDetailsClicked()
            ViewEvent.StartConnectionClicked -> onStartConnectionButtonClicked()
            ViewEvent.StopConnectionClicked -> onStopConnectionButtonClicked()
            ViewEvent.BackClicked -> onBackClicked()
        }
    }

    private fun onSeeDetailsClicked() {
        emit(OneShotEvent.GoToDetails)
    }

    private fun onStartConnectionButtonClicked() {
        startLiveDataService()
    }

    private fun onStopConnectionButtonClicked() {
        stopLiveDataService()
    }

    private fun onBackClicked() {
        if (state.connectionState is ConnectionUiState.CONNECTED) {
            stopLiveDataService()
        }
    }


    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _oneShotEvents.send(event)
        }
    }

    sealed interface OneShotEvent {
        object GoToDetails : OneShotEvent
    }

    sealed interface ViewEvent {
        object StartConnectionClicked: ViewEvent
        object StopConnectionClicked: ViewEvent
        object SeeDetailsClicked: ViewEvent
        object BackClicked: ViewEvent
    }

    private fun updateState(
        quasarsCurrentEnergy: Float = state.quasarsCurrentEnergy,
        totalQuasarsChargedEnergy: Float = state.totalQuasarsChargedEnergy,
        totalQuasarsDischargedEnergy: Float = state.totalQuasarsDischargedEnergy,
        quasarsPower: Float = state.quasarsPower,
        buildingDemandPower: Float = state.buildingDemandPower,
        solarPower: Float = state.solarPower,
        gridPower: Float = state.gridPower,
        connectionState: ConnectionUiState = state.connectionState
    ) {
        state = UiState(
            quasarsCurrentEnergy = quasarsCurrentEnergy,
            totalQuasarsChargedEnergy = totalQuasarsChargedEnergy,
            totalQuasarsDischargedEnergy = totalQuasarsDischargedEnergy,
            quasarsPower = quasarsPower,
            buildingDemandPower = buildingDemandPower,
            solarPower = solarPower,
            gridPower = gridPower,
            connectionState = connectionState
        )
    }
}