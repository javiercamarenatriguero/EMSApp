package com.akole.energyproviderapp.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akole.energyproviderapp.ui.screens.common.LoadingProgressBar
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

@Composable
fun Body(
    viewState: UiState
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)

        ) {
            if (viewState.isLoading) {
                LoadingProgressBar()
            } else {
                val lines = listOf(
                    listOf(
                        DataPoint(0f,2f),
                        DataPoint(1f,3f),
                        DataPoint(2f,1f),
                        DataPoint(3f,6f),
                        DataPoint(4f,7f),
                        DataPoint(5f,3f),
                        DataPoint(6f,2f)
                    ),
                    listOf(
                        DataPoint(0f,6f),
                        DataPoint(1f,4f),
                        DataPoint(2f,2f),
                        DataPoint(3f,8f),
                        DataPoint(4f,1.2f),
                        DataPoint(5f,5f),
                        DataPoint(6f,4.4f)
                    )
                )
                SampleLineGraph(lines = lines)
            }
        }
    }
}

@Composable
fun SampleLineGraph(lines: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(color = Color.LightGray),
                    LinePlot.Intersection(color = Color.Red),
                    LinePlot.Highlight(color = Color.Yellow),
                ),
                LinePlot.Line(
                    lines[1],
                    LinePlot.Connection(color = Color.DarkGray),
                    LinePlot.Intersection(color = Color.Magenta),
                    LinePlot.Highlight(color = Color.Cyan),
                )
            ),
            grid = LinePlot.Grid(Color.Gray, steps = 8),
            isZoomAllowed = true
        )
        ,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp),

        onSelection = { xLine, points ->
            // Do whatever you want here
        }
    )
}
