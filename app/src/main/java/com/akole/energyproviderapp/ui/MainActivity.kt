package com.akole.energyproviderapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.akole.energyproviderapp.ui.theme.EnergyProviderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnergyProviderApp {
                EnergyProviderAppContent()
            }
        }
    }
}

@Composable
private fun EnergyProviderApp(content: @Composable () -> Unit) {
    EnergyProviderAppTheme {
        Surface(color = MaterialTheme.colors.background){
            content()
        }
    }
}
