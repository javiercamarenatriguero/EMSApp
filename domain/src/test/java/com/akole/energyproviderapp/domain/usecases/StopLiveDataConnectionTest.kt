package com.akole.energyproviderapp.domain.usecases

import com.akole.energyproviderapp.domain.CoroutineTestRule
import com.akole.energyproviderapp.domain.datasources.EMSDataSource
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

class StopLiveDataConnectionTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var emsDataStore: EMSDataSource
    private lateinit var stopLiveDataConnection: StopLiveDataConnection

    @Before
    fun setUp() {
        emsDataStore = mockk()
    }

    @Test
    internal fun `WHEN StopConnection is called AND dataStore is Loading THEN the response is Loading`() {
        runTest {
            coEvery { emsDataStore.stopLiveDataConnection() } returns flowOf(StopLiveDataConnectionResponse.Loading)
            // Initialize
            stopLiveDataConnection = StopLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = stopLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(StopLiveDataConnectionResponse.Loading, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.stopLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN StopConnection is called AND dataStore is Error THEN the response is Error`() {
        runTest {
            val mockResponse = StopLiveDataConnectionResponse.Error(Exception())
            coEvery { emsDataStore.stopLiveDataConnection() } returns flowOf(mockResponse)
            // Initialize
            stopLiveDataConnection = StopLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = stopLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(mockResponse, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.stopLiveDataConnection() }
            clearAllMocks()
        }
    }

    @Test
    internal fun `WHEN StopConnection is called AND dataStore is responding Success THEN it returns Success`() {
        runTest {
            coEvery { emsDataStore.stopLiveDataConnection() } returns flowOf(StopLiveDataConnectionResponse.Success)
            // Initialize
            stopLiveDataConnection = StopLiveDataConnection(emsDataSource = emsDataStore)

            // Action
            val response = stopLiveDataConnection().first()

            // Asserts
            Assert.assertEquals(StopLiveDataConnectionResponse.Success, response)

            // Verify
            coVerify(exactly = 1) { emsDataStore.stopLiveDataConnection() }
            clearAllMocks()
        }
    }
}
