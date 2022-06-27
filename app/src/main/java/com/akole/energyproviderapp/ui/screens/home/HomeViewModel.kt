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
            showStartConnectionButton = true,
            isConnectionLoading = false,
            isConnectionError = false
        )
        startLiveDataService()
    }

    private fun startLiveDataService() {
        viewModelScope.launch {
            startLiveDataConnection().collect { response ->
                when (response) {
                    is StartLiveDataConnectionResponse.Loading -> {
                        updateState(
                            isConnectionLoading = true,
                            isConnectionError = false,
                            showStartConnectionButton = false
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
                            gridPower = response.energyLiveData.gridPower,
                            showStartConnectionButton = false,
                            isConnectionLoading = false,
                            isConnectionError = false
                        )
                    }
                    is StartLiveDataConnectionResponse.Error -> {
                        updateState(
                            isConnectionLoading = false,
                            isConnectionError = true,
                            showStartConnectionButton = true
                        )
                    }
                }
            }
        }
    }

    private fun stopLiveDataService() {
        viewModelScope.launch {
            stopLiveDataConnection().collect { response ->
                when (response) {
                    is StopLiveDataConnectionResponse.Success ->
                        updateState(
                            isConnectionError = false,
                            isConnectionLoading = false,
                            showStartConnectionButton = true
                        )
                    is StopLiveDataConnectionResponse.Error ->
                        updateState(
                            isConnectionError = true,
                            isConnectionLoading = false,
                            showStartConnectionButton = true
                        )
                    is StopLiveDataConnectionResponse.Loading ->
                        updateState(
                            isConnectionError = false,
                            isConnectionLoading = true,
                            showStartConnectionButton = false
                        )
                }
            }
        }
    }

    fun on(event: ViewEvent): Unit = with(event) {
        when (this) {
            ViewEvent.SeeDetailsClicked -> onSeeDetailsClicked()
            ViewEvent.StartConnectionClicked -> onStartConnectionButtonClicked()
            ViewEvent.OnBackPressed -> onBackPressed()
        }
    }

    private fun onSeeDetailsClicked() {
        emit(OneShotEvent.GoToDetails)
    }

    private fun onStartConnectionButtonClicked() {
        startLiveDataService()
    }

    private fun onBackPressed() {
        stopLiveDataService()
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
        object SeeDetailsClicked: ViewEvent
        object OnBackPressed: ViewEvent
    }

    private fun updateState(
        quasarsCurrentEnergy: Float = state.quasarsCurrentEnergy,
        totalQuasarsChargedEnergy: Float = state.totalQuasarsChargedEnergy,
        totalQuasarsDischargedEnergy: Float = state.totalQuasarsDischargedEnergy,
        quasarsPower: Float = state.quasarsPower,
        buildingDemandPower: Float = state.buildingDemandPower,
        solarPower: Float = state.solarPower,
        gridPower: Float = state.gridPower,
        showStartConnectionButton: Boolean = state.showStartConnectionButton,
        isConnectionLoading: Boolean = state.isConnectionLoading,
        isConnectionError: Boolean = state.isConnectionError
    ) {
        state = UiState(
            quasarsCurrentEnergy = quasarsCurrentEnergy,
            totalQuasarsChargedEnergy = totalQuasarsChargedEnergy,
            totalQuasarsDischargedEnergy = totalQuasarsDischargedEnergy,
            quasarsPower = quasarsPower,
            buildingDemandPower = buildingDemandPower,
            solarPower = solarPower,
            gridPower = gridPower,
            showStartConnectionButton = showStartConnectionButton,
            isConnectionLoading = isConnectionLoading,
            isConnectionError = isConnectionError
        )
    }
}