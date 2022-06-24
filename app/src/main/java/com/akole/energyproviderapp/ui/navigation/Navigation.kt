package com.akole.energyproviderapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.akole.energyproviderapp.ui.screens.details.DetailsScreen
import com.akole.energyproviderapp.ui.screens.home.HomeScreen


@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavFeature.HOME.route
    ) {
        homeNav()
        detailsNav()
    }
}

private fun NavGraphBuilder.homeNav() {
    navigation(
        startDestination = NavCommand.ContentType(NavFeature.HOME).route,
        route = NavFeature.HOME.route
    ) {
        composable(NavCommand.ContentType(NavFeature.HOME)) {
            HomeScreen()
        }
    }
}

private fun NavGraphBuilder.detailsNav() {
    navigation(
        startDestination = NavCommand.ContentType(NavFeature.DETAILS).route,
        route = NavFeature.DETAILS.route
    ) {
        composable(NavCommand.ContentType(NavFeature.DETAILS)) {
            DetailsScreen()
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}