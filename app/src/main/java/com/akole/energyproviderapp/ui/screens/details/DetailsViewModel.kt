package com.akole.energyproviderapp.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.usecases.GetHistoricalData
import com.akole.energyproviderapp.domain.usecases.GetHistoricalDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getHistoricalData: GetHistoricalData
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        updateState(
            historicalDataList = emptyList()
        )
        getHistoricalData()
    }

    private fun getHistoricalData() {
        viewModelScope.launch {
            getHistoricalData.invoke().collectLatest { response ->
                when (response) {
                    is GetHistoricalDataResponse.Loading ->
                        updateState(isLoading = true)
                    is GetHistoricalDataResponse.Success ->
                        updateState(
                            isLoading = false,
                            historicalDataList = response.data
                        )
                    is GetHistoricalDataResponse.Error ->
                        updateState(
                            isLoading = false,
                            historicalDataList = emptyList()
                        )
                }
            }
        }
    }

    private fun updateState(
        isLoading: Boolean = state.isLoading,
        historicalDataList: List<EnergyHistoricalData> = state.historicalDataList
    ) {
        state = UiState(
            isLoading = isLoading,
            historicalDataList = historicalDataList
        )
    }
}