package com.akole.energyproviderapp.ui.screens.home.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.screens.home.UiState

@Composable
fun QuasarDataCard(
    viewState: UiState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp
    ) {
        Column {
            QuasarEnergyData(
                quasarsTotalChargedEnergy = viewState.totalQuasarsChargedEnergy,
                quasarsTotalDischargedEnergy = viewState.totalQuasarsDischargedEnergy
            )
        }
    }
}

@Composable
fun QuasarEnergyData(
    quasarsTotalChargedEnergy: Float,
    quasarsTotalDischargedEnergy: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        QuasarEnergyWidget(
            icon = Icons.Default.KeyboardArrowUp,
            energyValue = quasarsTotalChargedEnergy,
            energyDescription = stringResource(id = R.string.home_quasar_charged_energy_text)
        )
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        QuasarEnergyWidget(
            icon = Icons.Default.KeyboardArrowDown,
            energyValue = quasarsTotalDischargedEnergy,
            energyDescription = stringResource(id = R.string.home_quasar_discharged_energy_text)
        )
    }
}

@Composable
fun QuasarEnergyWidget(
    icon: ImageVector,
    energyValue: Float,
    energyDescription: String
) {
    Column {
        Row{
            Icon(imageVector = icon, contentDescription = "")
            Text(
                text = stringResource(
                    id = R.string.home_energy_value_text,
                    String.format("%.3f", energyValue)
                ),
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = energyDescription,
            fontSize = 16.sp
        )
    }
}