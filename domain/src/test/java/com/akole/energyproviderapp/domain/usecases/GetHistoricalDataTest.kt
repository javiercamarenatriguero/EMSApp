package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.CoroutineTestRule
import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetHistoricalDataTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var emsDataStore: EMSDataSource
    private lateinit var getHistoricalData: GetHistoricalData

    @Before
    fun setUp() {
        emsDataStore = mockk()
    }

    @Test
    internal fun `WHEN GetHistorical is called AND dataStore is Loading THEN the response is Loading`() {
        runTest {
            coEvery { emsDataStore.getHistoricalData() } returns flowOf(GetHistoricalDataResponse.Loading)
            // Initialize
            getHistoricalData = GetHistoricalData(emsDataSource = emsDataStore)

            // Action
            val response = getHistoricalData().first()

            // Asserts
            Assert.assertEquals(GetHistoricalDataResponse.Loading, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.getHistoricalData() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN GetHistorical is called AND dataStore is Error THEN the response is Error`() {
        runTest {
            val mockResponse = GetHistoricalDataResponse.Error(Exception())
            coEvery { emsDataStore.getHistoricalData() } returns flowOf(mockResponse)
            // Initialize
            getHistoricalData = GetHistoricalData(emsDataSource = emsDataStore)

            // Action
            val response = getHistoricalData().first()

            // Asserts
            Assert.assertEquals(mockResponse, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.getHistoricalData() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN GetHistorical is called AND dataStore is responding Success THEN it returns data`() {
        runTest {
            val mockResponse = GetHistoricalDataResponse.Success(listOf(MOCK_HISTORICAL_DATA))
            coEvery { emsDataStore.getHistoricalData() } returns flowOf(mockResponse)
            // Initialize
            getHistoricalData = GetHistoricalData(emsDataSource = emsDataStore)

            // Action
            val response = getHistoricalData().first() as GetHistoricalDataResponse.Success

            // Asserts
            Assert.assertEquals(1, response.data.size)
            Assert.assertEquals(MOCK_QUASAR_POWER, response.data[0].quasarsPower)
            Assert.assertEquals(MOCK_GRID_POWER, response.data[0].gridActivePower)
            Assert.assertEquals(MOCK_SOLAR_POWER, response.data[0].solarActivePower)
            Assert.assertEquals(MOCK_BUILDING_POWER, response.data[0].buildingActivePower)
            Assert.assertEquals(MOCK_TIMESTAMP, response.data[0].timestamp)

            // Verify
            coVerify(exactly = 1) { emsDataStore.getHistoricalData() }
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
