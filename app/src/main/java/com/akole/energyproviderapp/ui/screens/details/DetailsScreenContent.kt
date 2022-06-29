package com.akole.energyproviderapp.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akole.energyproviderapp.ui.screens.common.Header

@Composable
fun DetailsScreenContent(
    viewState: UiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Header()
        Body(viewState = viewState)
    }
}
