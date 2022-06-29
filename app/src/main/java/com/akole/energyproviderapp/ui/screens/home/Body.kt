package com.akole.energyproviderapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.screens.common.LoadingProgressBar
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
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)

        ) {
            when (viewState.connectionState) {
                is ConnectionUiState.DISCONNECTED -> {
                    StartConnectionButton(onEventHandler = onEventHandler)
                }
                is ConnectionUiState.CONNECTING -> {
                    LoadingProgressBar()
                }
                is ConnectionUiState.CONNECTED -> {
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
}

@Composable
fun StartConnectionButton(
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit
) {
    Button(
        onClick = { onEventHandler(HomeViewModel.ViewEvent.StartConnectionClicked) },
        modifier = Modifier.padding(30.dp)
    ) {
        Text(text = stringResource(id = R.string.home_start_button_text))
    }
}

@Composable
fun StopConnectionButton(
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit
) {
    Button(onClick = {
        onEventHandler(HomeViewModel.ViewEvent.StopConnectionClicked)
    }) {
        Text(text = stringResource(id = R.string.home_stop_button_text))
    }
}