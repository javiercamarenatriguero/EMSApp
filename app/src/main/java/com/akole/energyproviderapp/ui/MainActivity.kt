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
import com.akole.energyproviderapp.ui.navigation.Navigation
import com.akole.energyproviderapp.ui.theme.EnergyProviderAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getHistoricalData: GetHistoricalData
    @Inject
    lateinit var startLiveDataConnection: StartLiveDataConnection
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
                        getHistoricalData().collect {
                            it
                        }
                        startLiveDataConnection().collect {
                            it
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