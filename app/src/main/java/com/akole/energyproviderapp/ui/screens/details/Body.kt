package com.akole.energyproviderapp.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.screens.common.LoadingProgressBar


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
            Box(
                modifier = Modifier.padding(20.dp)
            ) {
                if (viewState.isLoading) {
                    LoadingProgressBar()
                }else if (viewState.historicalDataList.isEmpty()) {
                    EmptyData()
                } else {
                    LineChart(viewState = viewState)
                }
            }
        }
    }
}

@Composable
fun EmptyData() {
    Text(
        text = stringResource(id = R.string.details_empty_data)
    )
}
