package com.akole.energyproviderapp.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.utils.details.parseToChartSeriesData
import hu.ma.charts.legend.data.LegendPosition
import hu.ma.charts.line.data.AxisLabel
import hu.ma.charts.line.data.ChartColors
import hu.ma.charts.line.data.DrawAxis
import hu.ma.charts.line.data.LineChartData

@Composable
fun LineChart(
    viewState: UiState
) {
    val axisLabelTextStyle = TextStyle(
        fontSize = 10.sp,
        color = ChartColors.defaultColors().xlabel
    )
    hu.ma.charts.line.LineChart(
        chartHeight = 500.dp,
        data = LineChartData(
            series = viewState.historicalDataList.parseToChartSeriesData(
                solarLabel = stringResource(id = R.string.details_solar_label),
                gridLabel = stringResource(id = R.string.details_grid_label),
                quasarLabel = stringResource(id = R.string.details_quasar_label),
                buildingLabel = stringResource(id = R.string.details_building_label)
            ),
            yLabels = listOf(
                AxisLabel(-80f, "-80 kW"),
                AxisLabel(-60f, "-40 kW"),
                AxisLabel(-40f, "-40 kW"),
                AxisLabel(-20f, "-20 kW"),
                AxisLabel(20f, "20 kW"),
                AxisLabel(40f, "40 kW"),
                AxisLabel(60f, "60 kW"),
                AxisLabel(80f, "80 kW"),
            ),
            drawAxis = DrawAxis.Both,
            xAxisTypeface = axisLabelTextStyle,
            yAxisTypeface = axisLabelTextStyle,
            horizontalLines = true,
            legendPosition = LegendPosition.Top,
        )
    )
}