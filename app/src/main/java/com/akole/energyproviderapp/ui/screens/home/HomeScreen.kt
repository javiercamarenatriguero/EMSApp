package com.akole.energyproviderapp.ui.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDetailsClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        HomeScreenContent(
            viewState = viewModel.state,
            onEventHandler = viewModel::on
        )
    }
    LaunchedEffect(viewModel.oneShotEvents) {
        viewModel.oneShotEvents.collect { event ->
            when(event) {
                is HomeViewModel.OneShotEvent.GoToDetails -> {
                    onDetailsClick()
                }
            }
        }
    }

    val activity = LocalContext.current as Activity
    BackHandler (enabled = true) {
        viewModel.on(HomeViewModel.ViewEvent.BackClicked)
        activity?.finish()
    }
}