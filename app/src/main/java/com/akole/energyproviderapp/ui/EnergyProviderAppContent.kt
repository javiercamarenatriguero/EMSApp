package com.akole.energyproviderapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.energyproviderapp.ui.navigation.Navigation

@Composable
fun EnergyProviderAppContent(
    appState: EnergyProviderAppState = rememberEnergyProviderAppState()
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = appState.currentToolbarText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Navigation(appState.navController)
        }
    }
}
