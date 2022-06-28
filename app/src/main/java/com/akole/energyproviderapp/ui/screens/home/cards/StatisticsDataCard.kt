package com.akole.energyproviderapp.ui.screens.home.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.screens.home.HomeViewModel
import com.akole.energyproviderapp.ui.screens.home.UiState
import com.akole.energyproviderapp.ui.utils.getCarElectricPowerPercentageToBuilding
import com.akole.energyproviderapp.ui.utils.getGridPowerPercentageToBuilding
import com.akole.energyproviderapp.ui.utils.getSolarPowerPercentageToBuilding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatisticsDataCard(
    viewState: UiState,
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        elevation = 10.dp,
        onClick = {
            onEventHandler(HomeViewModel.ViewEvent.SeeDetailsClicked)
        }
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            StatisticsPowerData(
                icon = Icons.Default.Power,
                value = viewState.getGridPowerPercentageToBuilding(),
                color = Color.Cyan
            )
            StatisticsPowerData(
                icon = Icons.Default.WbSunny,
                value = viewState.getSolarPowerPercentageToBuilding(),
                color = Color.Green
            )
            StatisticsPowerData(
                icon = Icons.Default.ElectricCar,
                value = viewState.getCarElectricPowerPercentageToBuilding(),
                color = Color.Red
            )
            Text(
                text = stringResource(id = R.string.home_see_more_details_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun StatisticsPowerData(
    icon: ImageVector,
    value: Float,
    color: Color
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(imageVector = icon, contentDescription = "")
        LinearProgressIndicator(
            progress = value / 100,
            color = color
        )
        Text(
            text = stringResource(
                id = R.string.home_statistics_value_text,
                String.format("%.1f", value)
            ),
            fontSize = 16.sp
        )
    }
}
