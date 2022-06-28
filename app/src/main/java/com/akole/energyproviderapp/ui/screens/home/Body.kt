package com.akole.energyproviderapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akole.energyproviderapp.ui.screens.home.cards.QuasarDataCard
import com.akole.energyproviderapp.ui.screens.home.cards.SourcesDataCard
import com.akole.energyproviderapp.ui.screens.home.cards.StatisticsDataCard

@Composable
fun Body(
    viewState: UiState,
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit
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
            if (viewState.isConnectionLoading) {
                LoadingProgress()
            } else if (viewState.isConnectionStopped) {
                StartConnectionButton(onEventHandler = onEventHandler)
            } else {
                QuasarDataCard(viewState = viewState)
                SourcesDataCard(viewState = viewState)
                StatisticsDataCard(
                    viewState = viewState,
                    onEventHandler = onEventHandler
                )
                StopConnectionButton(onEventHandler = onEventHandler)
            }
        }
    }
}

@Composable
fun LoadingProgress() {
    LinearProgressIndicator(
        backgroundColor = Color.LightGray,
        color = Color.Blue,
        modifier = Modifier.padding(30.dp)
    )
}

@Composable
fun StartConnectionButton(
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit
) {
    Button(
        onClick = { onEventHandler(HomeViewModel.ViewEvent.StartConnectionClicked) },
        modifier = Modifier.padding(30.dp)
    ) {
        Text(text = "Start Connection")
    }
}

@Composable
fun StopConnectionButton(
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit
) {
    Button(
        onClick = { onEventHandler(HomeViewModel.ViewEvent.StopConnectionClicked) }
    ) {
        Text(text = "Stop Connection")
    }
}