package com.akole.energyproviderapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            .verticalScroll(rememberScrollState())
    ) {
        Header()
        Body(
            viewState = viewState,
            onEventHandler = onEventHandler
        )
    }
}
