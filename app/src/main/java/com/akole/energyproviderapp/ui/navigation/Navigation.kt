package com.akole.energyproviderapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akole.energyproviderapp.ui.screens.details.DetailsScreen
import com.akole.energyproviderapp.ui.screens.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavFeature.HOME.route
    ) {
        homeNav(navController = navController)
        detailsNav()
    }
}

private fun NavGraphBuilder.homeNav(navController: NavController) {
    composable(NavFeature.HOME.route) {
        HomeScreen(
            onDetailsClick = {
                navController.navigate(NavFeature.DETAILS.route)
            }
        )
    }
}

private fun NavGraphBuilder.detailsNav() {
    composable(NavFeature.DETAILS.route) {
        DetailsScreen()
    }
}
