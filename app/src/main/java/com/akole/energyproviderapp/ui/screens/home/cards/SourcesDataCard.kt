package com.akole.energyproviderapp.ui.screens.home.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.screens.home.UiState

@Composable
fun SourcesDataCard(
    viewState: UiState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 5.dp,
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CardTitle(text = stringResource(id = R.string.home_sources_description))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                SourcePowerData(
                    sourceIcon = Icons.Default.Power,
                    sourcePower = viewState.gridPower,
                    sourcePowerDescription = stringResource(id = R.string.home_source_grid_text)
                )
                SourcePowerData(
                    sourceIcon = Icons.Default.WbSunny,
                    sourcePower = viewState.solarPower,
                    sourcePowerDescription = stringResource(id = R.string.home_source_solar_text)
                )
                SourcePowerData(
                    sourceIcon = Icons.Default.ElectricCar,
                    sourcePower = viewState.quasarsPower,
                    sourcePowerDescription = stringResource(id = R.string.home_source_quasar_text)
                )
            }
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(5.dp)
            )
            BuildingDemandData(
                sourceIcon = Icons.Default.Home,
                power = viewState.buildingDemandPower)
        }
    }
}

@Composable
fun SourcePowerData(
    sourceIcon: ImageVector,
    sourcePower: Float,
    sourcePowerDescription: String
) {
    Column (
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(imageVector = sourceIcon, contentDescription = "")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(
                    id = R.string.home_power_value_text,
                    String.format("%.1f", sourcePower)
                ),
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = sourcePowerDescription,
            fontSize = 12.sp
        )
    }
}

@Composable
fun BuildingDemandData(
    sourceIcon: ImageVector,
    power: Float
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(imageVector = sourceIcon, contentDescription = "")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(
                    id = R.string.home_power_value_text,
                    String.format("%.1f", power)
                ),
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.home_building_demand_text),
            fontSize = 12.sp
        )
    }
}
