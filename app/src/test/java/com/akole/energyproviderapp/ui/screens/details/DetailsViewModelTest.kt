package com.akole.energyproviderapp.ui.screens.details

import com.akole.energyproviderapp.CoroutineTestRule
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import com.akole.energyproviderapp.domain.usecases.*
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var getHistoricalData: GetHistoricalData
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setUp() {
        getHistoricalData = mockk()
    }

    @Test
    internal fun `GIVEN a initialized ViewModel WHEN getHistoricalData is called AND its response is Loading THEN the uIState is Loading`() {
        runTest {
            coEvery { getHistoricalData() } returns flowOf(GetHistoricalDataResponse.Loading)
            viewModel = DetailsViewModel(
                getHistoricalData = getHistoricalData
            )
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(true, viewModel.state.isLoading)

            // Verify
            coVerify (exactly = 1) { getHistoricalData() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `GIVEN a initialized ViewModel WHEN getHistoricalData is called AND its response is Error THEN the uIState is updated`() {
        runTest {
            coEvery { getHistoricalData() } returns flowOf(GetHistoricalDataResponse.Error(Exception()))
            viewModel = DetailsViewModel(
                getHistoricalData = getHistoricalData
            )
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(false, viewModel.state.isLoading)
            Assert.assertEquals(emptyList<EnergyHistoricalData>(), viewModel.state.historicalDataList)

            // Verify
            coVerify (exactly = 1) { getHistoricalData() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `GIVEN a initialized ViewModel WHEN getHistoricalData is called AND its response Success THEN the uIState's data is updated`() {
        runTest {
            coEvery { getHistoricalData() } returns flowOf(GetHistoricalDataResponse.Success(listOf(MOCK_HISTORICAL_DATA)))
            viewModel = DetailsViewModel(
                getHistoricalData = getHistoricalData
            )
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(false, viewModel.state.isLoading)
            Assert.assertEquals(1, viewModel.state.historicalDataList.size)
            Assert.assertEquals(MOCK_SOLAR_POWER, viewModel.state.historicalDataList[0].solarActivePower)
            Assert.assertEquals(MOCK_GRID_POWER, viewModel.state.historicalDataList[0].gridActivePower)
            Assert.assertEquals(MOCK_QUASAR_POWER, viewModel.state.historicalDataList[0].quasarsPower)
            Assert.assertEquals(MOCK_BUILDING_POWER, viewModel.state.historicalDataList[0].buildingActivePower)
            Assert.assertEquals(MOCK_TIMESTAMP, viewModel.state.historicalDataList[0].timestamp)

            // Verify
            coVerify (exactly = 1) { getHistoricalData() }
            clearAllMocks()
        }
    }

    companion object {
        private const val MOCK_SOLAR_POWER = 1.0f
        private const val MOCK_GRID_POWER = 20.0f
        private const val MOCK_BUILDING_POWER = 30.0f
        private const val MOCK_QUASAR_POWER = 40.0f
        private const val MOCK_TIMESTAMP = 1_111L
        private val MOCK_HISTORICAL_DATA =
            EnergyHistoricalData(
                solarActivePower = MOCK_SOLAR_POWER,
                gridActivePower = MOCK_GRID_POWER,
                buildingActivePower = MOCK_BUILDING_POWER,
                quasarsPower = MOCK_QUASAR_POWER,
                timestamp = MOCK_TIMESTAMP
            )
    }
}