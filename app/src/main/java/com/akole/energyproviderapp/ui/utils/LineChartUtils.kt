package com.akole.energyproviderapp.ui.utils

import androidx.compose.ui.graphics.Color
import com.akole.energyproviderapp.domain.models.EnergyHistoricalData
import hu.ma.charts.line.data.LineChartData

fun List<EnergyHistoricalData>.parseToChartSeriesData(
    solarLabel: String,
    gridLabel: String,
    quasarLabel: String,
    buildingLabel: String
): List<LineChartData.SeriesData> {
    val solarPoints= this
        .mapIndexed { index, data ->
            LineChartData.SeriesData.Point(index, data.solarActivePower)
        }
    val gridPoints = this
        .mapIndexed { index, data ->
            LineChartData.SeriesData.Point(index, data.gridActivePower)
        }
        .toList()
    val quasarPoints = this
        .mapIndexed { index, data ->
            LineChartData.SeriesData.Point(index, data.quasarsPower )
        }
        .toList()
    val buildingPoints = this
        .mapIndexed { index, energyHistoricalData ->
            LineChartData.SeriesData.Point(index, energyHistoricalData.buildingActivePower)
        }

    return listOf(
        getSeriesData(
            points = solarPoints,
            color = Color.Green,
            title = solarLabel
        ),
        getSeriesData(
            points = gridPoints,
            color = Color.Cyan,
            title = gridLabel
        ),
        getSeriesData(
            points = quasarPoints,
            color = Color.Red,
            title = quasarLabel
        ),
        getSeriesData(
            points = buildingPoints,
            color = Color.Blue,
            title = buildingLabel
        )
    )
}

private fun getSeriesData(
    points: List<LineChartData.SeriesData.Point>,
    color: Color,
    title: String
) =
    LineChartData.SeriesData(
        title,
        points = points,
        color
    )