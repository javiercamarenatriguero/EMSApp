package com.akole.energyproviderapp.ui.screens.home

import com.akole.energyproviderapp.CoroutineTestRule
import com.akole.energyproviderapp.domain.models.EnergyLiveData
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnectionResponse
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnectionResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var startLiveDataConnection: StartLiveDataConnection
    private lateinit var stopLiveDataConnection: StopLiveDataConnection
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        startLiveDataConnection = mockk()
        stopLiveDataConnection = mockk()
        viewModel = HomeViewModel(
            startLiveDataConnection = startLiveDataConnection,
            stopLiveDataConnection = stopLiveDataConnection
        )
    }

    @Test
    internal fun `WHEN the ViewModel is created THEN the ViewState is initialized`() {
        // Asserts
        Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)
        Assert.assertEquals(0.0f, viewModel.state.quasarsPower)
        Assert.assertEquals(0.0f, viewModel.state.gridPower)
        Assert.assertEquals(0.0f, viewModel.state.solarPower)
        Assert.assertEquals(0.0f, viewModel.state.buildingDemandPower)
        Assert.assertEquals(0.0f, viewModel.state.quasarsCurrentEnergy)
        Assert.assertEquals(0.0f, viewModel.state.totalQuasarsChargedEnergy)
        Assert.assertEquals(0.0f, viewModel.state.totalQuasarsDischargedEnergy)

        clearAllMocks()
    }

    @Test
    internal fun `WHEN the StartConnection button is clicked AND the response is Success THEN the ViewModel uiState is Connected`() {
        runTest {
            coEvery { startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Success)
            // Action
            viewModel.on(HomeViewModel.ViewEvent.StartConnectionClicked)
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(ConnectionUiState.CONNECTED, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 1) { startLiveDataConnection() }
            coVerify (exactly = 0) { stopLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN the StartConnection button is clicked AND response is Loading THEN the ViewModel uiState is Connectiog`() {
        runTest {
            // Mocks
            coEvery { startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Loading)

            // Action
            viewModel.on(HomeViewModel.ViewEvent.StartConnectionClicked)
            advanceUntilIdle()

            // Asserts
            Assert.assertEquals(ConnectionUiState.CONNECTING, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 1) { startLiveDataConnection() }
            coVerify (exactly = 0) { stopLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN the StartConnection button is clicked AND response is an Error THEN the ViewModel uiState is Disconnected`() {
        runTest {
            // Mocks
            coEvery { startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Error(Exception()))

            // Action
            viewModel.on(HomeViewModel.ViewEvent.StartConnectionClicked)
            advanceUntilIdle()

            // Asserts
            Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 1) { startLiveDataConnection() }
            coVerify (exactly = 0) { stopLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN the StartConnection button is clicked AND response is a Data THEN the ViewModel uiState updates to its values`() {
        runTest {
            // Mocks
            coEvery { startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.OnData(MOCK_LIVE_DATA))

            // Action
            viewModel.on(HomeViewModel.ViewEvent.StartConnectionClicked)
            advanceUntilIdle()

            // Asserts
            Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)
            Assert.assertEquals(MOCK_QUASAR_POWER, viewModel.state.quasarsPower)
            Assert.assertEquals(MOCK_GRID_POWER, viewModel.state.gridPower)
            Assert.assertEquals(MOCK_SOLAR_POWER, viewModel.state.solarPower)
            Assert.assertEquals(MOCK_BUILDING_POWER, viewModel.state.buildingDemandPower)
            Assert.assertEquals(MOCK_QUASAR_CURRENT_ENERGY, viewModel.state.quasarsCurrentEnergy)
            Assert.assertEquals(MOCK_QUASAR_CHARGED_ENERGY, viewModel.state.totalQuasarsChargedEnergy)
            Assert.assertEquals(MOCK_QUASAR_DISCHARGED_ENERGY, viewModel.state.totalQuasarsDischargedEnergy)

            // Verify
            coVerify (exactly = 1) { startLiveDataConnection() }
            coVerify (exactly = 0) { stopLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN the StopConnection button is clicked AND response is Success THEN the ViewModel uiState is Disconnected`() {
        runTest {
            // Mocks
            coEvery { stopLiveDataConnection() } returns flowOf(StopLiveDataConnectionResponse.Success)

            // Action
            viewModel.on(HomeViewModel.ViewEvent.StopConnectionClicked)
            advanceUntilIdle()

            // Asserts
            Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 0) { startLiveDataConnection() }
            coVerify (exactly = 1) { stopLiveDataConnection() }

            clearAllMocks()
        }
    }

    @Test
    internal fun `GIVEN started session WHEN the StopConnection button is clicked AND response is Success THEN the ViewModel uiState is disconnected`() {
        runTest {
            // Mocks
            coEvery { startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Success)
            coEvery { stopLiveDataConnection() } returns flowOf(StopLiveDataConnectionResponse.Success)

            // Action (Connect)
            viewModel.on(HomeViewModel.ViewEvent.StartConnectionClicked)
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(ConnectionUiState.CONNECTED, viewModel.state.connectionState)

            // Action (Disconnect)
            viewModel.on(HomeViewModel.ViewEvent.StopConnectionClicked)
            advanceUntilIdle()

            // Asserts
            Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 1) { startLiveDataConnection() }
            coVerify (exactly = 1) { stopLiveDataConnection() }

            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN the Details is clicked THEN the ViewModel change the state of oneShotEvent`() {
        runTest {
            // Action (Connect)
            viewModel.on(HomeViewModel.ViewEvent.SeeDetailsClicked)
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(HomeViewModel.OneShotEvent.GoToDetails, viewModel.oneShotEvents.first())

            // Verify
            coVerify (exactly = 0) { startLiveDataConnection() }
            coVerify (exactly = 0) { stopLiveDataConnection() }

            clearAllMocks()
        }
    }

    @Test
    internal fun `GIVEN started session WHEN the Back button is clicked THEN the stopConnection is called`() {
        runTest {
            // Mocks
            coEvery { startLiveDataConnection() } returns flowOf(StartLiveDataConnectionResponse.Success)
            coEvery { stopLiveDataConnection() } returns flowOf(StopLiveDataConnectionResponse.Success)

            // Action (Connect)
            viewModel.on(HomeViewModel.ViewEvent.StartConnectionClicked)
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(ConnectionUiState.CONNECTED, viewModel.state.connectionState)

            // Action (Back Button)
            viewModel.on(HomeViewModel.ViewEvent.BackClicked)
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 1) { startLiveDataConnection() }
            coVerify (exactly = 1) { stopLiveDataConnection() }

            clearAllMocks()
        }
    }

    @Test
    internal fun `GIVEN not started session WHEN the Back button is clicked THEN the stopConnection not is called`() {
        runTest {
            // Mocks
            coEvery { stopLiveDataConnection() } returns flowOf(StopLiveDataConnectionResponse.Success)

            // Action (Back Button)
            viewModel.on(HomeViewModel.ViewEvent.BackClicked)
            advanceUntilIdle()
            // Asserts
            Assert.assertEquals(ConnectionUiState.DISCONNECTED, viewModel.state.connectionState)

            // Verify
            coVerify (exactly = 0) { startLiveDataConnection() }
            coVerify (exactly = 0) { stopLiveDataConnection() }

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