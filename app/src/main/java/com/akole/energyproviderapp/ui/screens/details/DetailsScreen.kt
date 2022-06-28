package com.akole.energyproviderapp.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        DetailsScreenContent(
            viewState = viewModel.state
        )
    }
}