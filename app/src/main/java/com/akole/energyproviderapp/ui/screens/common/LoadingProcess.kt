package com.akole.energyproviderapp.ui.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun LoadingProgressBar() {
    LinearProgressIndicator(
        backgroundColor = Color.LightGray,
        color = Color.Blue,
        modifier = Modifier
            .padding(30.dp)
            .testTag(PASS_SHOW_LOADING_BAR_TEST_TAG)
    )
}

internal const val PASS_SHOW_LOADING_BAR_TEST_TAG = "PassShowLoadingBarTestTag"