package com.akole.energyproviderapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akole.energyproviderapp.domain.usecases.GetHistoricalData
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnection
import com.akole.energyproviderapp.ui.navigation.Navigation
import com.akole.energyproviderapp.ui.theme.EnergyProviderAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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
                    LaunchedEffect(true) {
                        startLiveDataConnection().collect {
                            println("start -> ${it}")
                        }
                    }

                    LaunchedEffect(true) {
                        delay(20000)
                        getHistoricalData().collect {
                            it
                            println("received -> ${it}")
                        }
                        delay(2000)
                        stopLiveDataConnection().collect {
                            it
                            println("stop -> ${it}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EnergyProviderAppTheme {
        Greeting("Android")
    }
}