package com.akole.energyproviderapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akole.energyproviderapp.ui.screens.common.Header

@Composable
fun HomeScreenContent(
    viewState: UiState,
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Header()
        Body(
            viewState = viewState,
            onEventHandler = onEventHandler
        )
    }
}
