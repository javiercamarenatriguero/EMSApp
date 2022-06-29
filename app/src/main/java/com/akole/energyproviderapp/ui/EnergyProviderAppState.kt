package com.akole.energyproviderapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akole.energyproviderapp.R
import com.akole.energyproviderapp.ui.navigation.NavFeature

@Composable
fun rememberEnergyProviderAppState(
    navController: NavHostController = rememberNavController()
): EnergyProviderAppState = remember(navController) {
    EnergyProviderAppState(navController)
}

class EnergyProviderAppState(
    val navController: NavHostController,
) {
    val currentToolbarText: String
        @Composable get() = when(currentRoute) {
            NavFeature.HOME.route -> stringResource(id = R.string.home_toolbar)
            NavFeature.DETAILS.route -> stringResource(id = R.string.details_toolbar)
            else -> ""
        }

    private val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""
}