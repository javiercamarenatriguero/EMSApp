package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.CoroutineTestRule
import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import com.akole.energyproviderapp.domain.models.EnergyLiveData
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

class StartLiveDataConnectionTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var emsDataStore: EMSDataSource
    private lateinit var startLiveDataConnection: StartLiveDataConnection

    @Before
    fun setUp() {
        emsDataStore = mockk()
    }

    @Test
    internal fun `WHEN StartConnection is called AND dataStore is Loading THEN the response is Loading`() {
        runTest {
            coEvery { emsDataStore.startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Loading)
            // Initialize
            startLiveDataConnection = StartLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = startLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(StartLiveDataConnectionResponse.Loading, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.startLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN StartConnection is called AND dataStore is Error THEN the response is Error`() {
        runTest {
            val mockResponse = StartLiveDataConnectionResponse.Error(Exception())
            coEvery { emsDataStore.startLiveDataConnection() } returns flowOf(mockResponse)
            // Initialize
            startLiveDataConnection = StartLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = startLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(mockResponse, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.startLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN StartConnection is called AND dataStore is responding Success THEN it returns Success`() {
        runTest {
            coEvery { emsDataStore.startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Success)
            // Initialize
            startLiveDataConnection = StartLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = startLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(StartLiveDataConnectionResponse.Success, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.startLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN StartConnection is called AND dataStore is responding onData THEN it returns data`() {
        runTest {
            // Mock
            val mockResponse = StartLiveDataConnectionResponse.OnData(MOCK_LIVE_DATA)
            coEvery { emsDataStore.startLiveDataConnection() } returns flowOf(mockResponse)

            // Initialize
            startLiveDataConnection = StartLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = startLiveDataConnection().first() as StartLiveDataConnectionResponse.OnData

            // Asserts
            Assert.assertEquals(mockResponse, response)
            Assert.assertEquals(MOCK_QUASAR_POWER, response.energyLiveData.quasarsPower)
            Assert.assertEquals(MOCK_GRID_POWER, response.energyLiveData.gridPower)
            Assert.assertEquals(MOCK_SOLAR_POWER, response.energyLiveData.solarPower)
            Assert.assertEquals(MOCK_BUILDING_POWER, response.energyLiveData.buildingPowerDemand)
            Assert.assertEquals(MOCK_QUASAR_CURRENT_ENERGY, response.energyLiveData.quasarsCurrentEnergy)
            Assert.assertEquals(MOCK_QUASAR_CHARGED_ENERGY, response.energyLiveData.quasarsTotalChargedEnergy)
            Assert.assertEquals(MOCK_QUASAR_DISCHARGED_ENERGY, response.energyLiveData.quasarsTotalDischargedEnergy)

            // Verify
            coVerify(exactly = 1) { emsDataStore.startLiveDataConnection() }
            clearAllMocks()
        }
    }

    companion object {
        private const val MOCK_SOLAR_POWER = 1.0f
        private const val MOCK_GRID_POWER = 20.0f
        private const val MOCK_BUILDING_POWER = 30.0f
        private const val MOCK_QUASAR_POWER = 40.0f
        private const val MOCK_QUASAR_CURRENT_ENERGY = 2.0f
        private const val MOCK_QUASAR_CHARGED_ENERGY = 10.0f
        private const val MOCK_QUASAR_DISCHARGED_ENERGY = 20.0f
        private val MOCK_LIVE_DATA =
            EnergyLiveData(
                solarPower = MOCK_SOLAR_POWER,
                gridPower = MOCK_GRID_POWER,
                buildingPowerDemand = MOCK_BUILDING_POWER,
                quasarsPower = MOCK_QUASAR_POWER,
                quasarsCurrentEnergy = MOCK_QUASAR_CURRENT_ENERGY,
                quasarsTotalDischargedEnergy = MOCK_QUASAR_DISCHARGED_ENERGY,
                quasarsTotalChargedEnergy = MOCK_QUASAR_CHARGED_ENERGY
            )
    }
}
