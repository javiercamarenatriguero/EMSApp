package com.akole.energyproviderapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.akole.energyproviderapp.domain.usecases.GetHistoricalData
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnection
import com.akole.energyproviderapp.ui.navigation.Navigation
import com.akole.energyproviderapp.ui.theme.EnergyProviderAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getHistoricalData: GetHistoricalData
    @Inject
    lateinit var startLiveDataConnection: StartLiveDataConnection
    @Inject
    lateinit var stopLiveDataConnection: StopLiveDataConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EnergyProviderAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(rememberEnergyProviderAppState().navController)
                }
            }
        }
    }
}
