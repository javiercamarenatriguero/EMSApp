package com.akole.energyproviderapp.ui.screens.home

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.screens.common.PASS_SHOW_LOADING_BAR_TEST_TAG

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class HomeScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun show_start_connection_button_when_disconnected(): Unit = with(composeTestRule) {
        var wasCalled = false
        composeTestRule.setContent {

            HomeScreenContent(
                viewState = MOCK_DISCONNECTED_STATE,
                onEventHandler = { wasCalled = it == HomeViewModel.ViewEvent.StartConnectionClicked }
            )
        }
        composeTestRule.onNodeWithText(context.getString(R.string.home_start_button_text)).assertHasClickAction()
        composeTestRule.onNodeWithText(context.getString(R.string.home_start_button_text)).performClick()
        assert(value = wasCalled)
        onNodeWithTag(PASS_SHOW_LOADING_BAR_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun show_connecting_progress_bar_is_shown_when_connecting(): Unit = with(composeTestRule) {
        setContent {
            HomeScreenContent(
                viewState = MOCK_CONNECTING_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithTag(PASS_SHOW_LOADING_BAR_TEST_TAG).assertExists()
        onNodeWithText(context.getString(R.string.home_start_button_text)).assertDoesNotExist()
        onNodeWithText(context.getString(R.string.home_stop_button_text)).assertDoesNotExist()
    }

    @Test
    fun show_stop_connection_button_when_is_connected(): Unit = with(composeTestRule) {
        setContent {
            HomeScreenContent(
                viewState = MOCK_CONNECTED_STATE,
                onEventHandler = { }
            )
        }
        composeTestRule.onNodeWithText(context.getString(R.string.home_stop_button_text)).assertHasClickAction()
        composeTestRule.onNodeWithText(context.getString(R.string.home_stop_button_text)).performClick()
        onNodeWithText(context.getString(R.string.home_start_button_text)).assertDoesNotExist()
    }

    @Test
    fun show_energy_values_when_is_connected(): Unit = with(composeTestRule) {
        setContent {
            HomeScreenContent(
                viewState = MOCK_CONNECTED_STATE,
                onEventHandler = {}
            )
        }
        val chargedStringValue = "10.000 kWh"
        val dischargedStringValue = "20.000 kWh"

        onNodeWithText(context.getString(R.string.home_quasar_charged_energy_text)).assertExists()
        onNodeWithText(context.getString(R.string.home_quasar_discharged_energy_text)).assertExists()
        onNodeWithText(chargedStringValue).assertExists()
        onNodeWithText(dischargedStringValue).assertExists()
    }

    @Test
    fun show_power_values_when_is_connected(): Unit = with(composeTestRule) {
        setContent {
            HomeScreenContent(
                viewState = MOCK_CONNECTED_STATE,
                onEventHandler = {}
            )
        }
        val solarPowerStringValue = "80.0 kW"
        val gridPowerStringValue = "40.0 kW"
        val buildingPowerStringValue = "100.0 kW"
        val quasarPowerStringValue = "20.0 kW"

        onNodeWithText(context.getString(R.string.home_source_solar_text)).assertExists()
        onNodeWithText(context.getString(R.string.home_source_grid_text)).assertExists()
        onNodeWithText(context.getString(R.string.home_building_demand_text)).assertExists()
        onNodeWithText(context.getString(R.string.home_source_quasar_text)).assertExists()
        onNodeWithText(solarPowerStringValue).assertExists()
        onNodeWithText(gridPowerStringValue).assertExists()
        onNodeWithText(buildingPowerStringValue).assertExists()
        onNodeWithText(quasarPowerStringValue).assertExists()
    }

    @Test
    fun show_source_percentages_when_is_connected(): Unit = with(composeTestRule) {
        setContent {
            HomeScreenContent(
                viewState = MOCK_CONNECTED_STATE,
                onEventHandler = {}
            )
        }
        val solarPercentageStringValue = "80.0 %"
        val gridPercentageStringValue = "20.0 %"
        val quasarPercentageStringValue = "0.0 %"

        onNodeWithText(context.getString(R.string.home_statistics_description)).assertExists()
        onNodeWithText(solarPercentageStringValue).assertExists()
        onNodeWithText(gridPercentageStringValue).assertExists()
        onNodeWithText(quasarPercentageStringValue).assertExists()
    }

    @Test
    fun check_if_see_details_is_clicked(): Unit = with(composeTestRule) {
        var wasCalled = false
        setContent {
            HomeScreenContent(
                viewState = MOCK_CONNECTED_STATE,
                onEventHandler = {wasCalled = true}
            )
        }
        composeTestRule.onNodeWithText(context.getString(R.string.home_see_more_details_text)).assertHasClickAction()
        composeTestRule.onNodeWithText(context.getString(R.string.home_see_more_details_text)).performClick()
        assert(value = wasCalled)
    }

    companion object {
        private const val MOCK_SOLAR_POWER = 80.0f
        private const val MOCK_GRID_POWER = 40.0f
        private const val MOCK_BUILDING_POWER = 100.0f
        private const val MOCK_QUASAR_POWER = 20.0f
        private const val MOCK_QUASAR_CURRENT_ENERGY = 2.0f
        private const val MOCK_QUASAR_CHARGED_ENERGY = 10.0f
        private const val MOCK_QUASAR_DISCHARGED_ENERGY = 20.0f

        private val MOCK_DISCONNECTED_STATE = UiState(
            connectionState = ConnectionUiState.DISCONNECTED
        )
        private val MOCK_CONNECTING_STATE = UiState(
            connectionState = ConnectionUiState.CONNECTING
        )
        private val MOCK_CONNECTED_STATE = UiState(
            connectionState = ConnectionUiState.CONNECTED,
            quasarsPower = MOCK_QUASAR_POWER,
            gridPower = MOCK_GRID_POWER,
            solarPower = MOCK_SOLAR_POWER,
            buildingDemandPower = MOCK_BUILDING_POWER,
            quasarsCurrentEnergy = MOCK_QUASAR_CURRENT_ENERGY,
            totalQuasarsChargedEnergy = MOCK_QUASAR_CHARGED_ENERGY,
            totalQuasarsDischargedEnergy = MOCK_QUASAR_DISCHARGED_ENERGY
        )
    }
}