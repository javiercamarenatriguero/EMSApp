package com.akole.energyproviderapp.data.datasources

import com.akole.energyproviderapp.data.CoroutineTestRule
import com.akole.energyproviderapp.data.adapter.QuasarChargerAdapter
import com.akole.energyproviderapp.domain.models.EnergyLiveData
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnectionResponse
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnectionResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuasarChargerDataSourceTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var adapter: QuasarChargerAdapter
    private lateinit var quasarChargerDataSource: QuasarChargerDataSource

    @Before
    fun setUp() {
        adapter = mockk()
    }

    @Test
    internal fun `WHEN startLiveDataConnection is called AND adapter is Loading THEN the response is Loading`() {
        runTest {
            // Mock
            coEvery { adapter.startLiveData() } returns Unit
            coEvery { adapter.liveData } returns flowOf(MOCK_ENERGY_LIVE_DATA)

            // Initialize
            quasarChargerDataSource = QuasarChargerDataSource(adapter = adapter)

            // Action
            val response = quasarChargerDataSource.startLiveDataConnection().toList()
            val loadingResponse = response[0]
            val successResponse = response[1]
            val onDataResponse = response[2] as StartLiveDataConnectionResponse.OnData

            // Asserts
            Assert.assertEquals(StartLiveDataConnectionResponse.Loading, loadingResponse)
            Assert.assertEquals(StartLiveDataConnectionResponse.Success, successResponse)
            Assert.assertEquals(MOCK_ENERGY_LIVE_DATA, onDataResponse.energyLiveData)

            // Verify
            coVerify(exactly = 1) { adapter.startLiveData() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN stopLiveDataConnection is called THEN the response is Success`() {
        runTest {
            // Mock
            coEvery { adapter.stopLiveData() } returns Unit

            // Initialize
            quasarChargerDataSource = QuasarChargerDataSource(adapter = adapter)

            // Action
            val response = quasarChargerDataSource.stopLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(StopLiveDataConnectionResponse.Success, response)

            // Verify
            coVerify(exactly = 1) { adapter.stopLiveData() }
            clearAllMocks()
        }
    }

    companion object {
        private const val MOCK_SOLAR_POWER = 1.0f
        private const val MOCK_GRID_POWER = 20.0f
        private const val MOCK_BUILDING_POWER = 30.0f
        private const val MOCK_QUASAR_POWER = 40.0f

        // Energy values
        private const val MOCK_QUASAR_CURRENT_ENERGY = 2.0f
        private const val MOCK_QUASAR_CHARGED_ENERGY = 10.0f
        private const val MOCK_QUASAR_DISCHARGED_ENERGY = 20.0f

        private val MOCK_ENERGY_LIVE_DATA = EnergyLiveData(
            solarPower = MOCK_SOLAR_POWER,
            gridPower = MOCK_GRID_POWER,
            quasarsPower = MOCK_QUASAR_POWER,
            buildingPowerDemand = MOCK_BUILDING_POWER,
            quasarsCurrentEnergy = MOCK_QUASAR_CURRENT_ENERGY,
            quasarsTotalDischargedEnergy = MOCK_QUASAR_DISCHARGED_ENERGY,
            quasarsTotalChargedEnergy = MOCK_QUASAR_CHARGED_ENERGY
        )
    }
}