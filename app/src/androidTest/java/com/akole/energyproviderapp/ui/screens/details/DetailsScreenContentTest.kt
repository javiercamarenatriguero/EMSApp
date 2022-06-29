package com.akole.energyproviderapp.ui.screens.details

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.ui.screens.common.PASS_SHOW_LOADING_BAR_TEST_TAG

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class DetailsScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun show_loading_progress_bar_when_connecting(): Unit = with(composeTestRule) {
        composeTestRule.setContent {
            DetailsScreenContent(
                viewState = MOCK_CONNECTING_STATE
            )
        }
        onNodeWithTag(PASS_SHOW_LOADING_BAR_TEST_TAG).assertExists()
        onNodeWithText(context.getString(R.string.details_empty_data)).assertDoesNotExist()
    }

    @Test
    fun show_empty_message__when_data_empty(): Unit = with(composeTestRule) {
        composeTestRule.setContent {
            DetailsScreenContent(
                viewState = MOCK_EMPTY_STATE
            )
        }
        onNodeWithText(context.getString(R.string.details_empty_data)).assertExists()
        onNodeWithTag(PASS_SHOW_LOADING_BAR_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun show_chart_when_data_contains_values(): Unit = with(composeTestRule) {
        composeTestRule.setContent {
            DetailsScreenContent(
                viewState = MOCK_CHART_STATE
            )
        }
        onNodeWithText(context.getString(R.string.details_quasar_label)).assertExists()
        onNodeWithText(context.getString(R.string.details_grid_label)).assertExists()
        onNodeWithText(context.getString(R.string.details_solar_label)).assertExists()
        onNodeWithText(context.getString(R.string.details_building_label)).assertExists()

        onNodeWithText(context.getString(R.string.details_empty_data)).assertDoesNotExist()
        onNodeWithTag(PASS_SHOW_LOADING_BAR_TEST_TAG).assertDoesNotExist()
    }


    companion object {
        private const val MOCK_SOLAR_POWER = 80.0f
        private const val MOCK_GRID_POWER = 40.0f
        private const val MOCK_BUILDING_POWER = 100.0f
        private const val MOCK_QUASAR_POWER = 20.0f
        private const val MOCK_TIMESTAMP_MS = 1_000L

        private val MOCK_ENERGY_HISTORICAL_DATA_1 = EnergyHistoricalData(
            solarActivePower = MOCK_SOLAR_POWER,
            gridActivePower = MOCK_GRID_POWER,
            quasarsPower = MOCK_QUASAR_POWER,
            buildingActivePower = MOCK_BUILDING_POWER,
            timestamp = MOCK_TIMESTAMP_MS
        )
        private val MOCK_ENERGY_HISTORICAL_DATA_2 = EnergyHistoricalData(
            solarActivePower = MOCK_SOLAR_POWER,
            gridActivePower = MOCK_GRID_POWER,
            quasarsPower = MOCK_QUASAR_POWER,
            buildingActivePower = MOCK_BUILDING_POWER,
            timestamp = MOCK_TIMESTAMP_MS + MOCK_TIMESTAMP_MS
        )

        private val MOCK_CONNECTING_STATE = UiState(
            isLoading = true
        )
        private val MOCK_EMPTY_STATE = UiState(
            isLoading = false,
            historicalDataList = emptyList()
        )
        private val MOCK_CHART_STATE = UiState(
            isLoading = false,
            historicalDataList = listOf(MOCK_ENERGY_HISTORICAL_DATA_1, MOCK_ENERGY_HISTORICAL_DATA_2)
        )
    }
}