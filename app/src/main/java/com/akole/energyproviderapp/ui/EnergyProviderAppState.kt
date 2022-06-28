package com.akole.energyproviderapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akole.energyproviderapp.ui.navigation.NavFeature
import com.akole.energyproviderapp.ui.navigation.navigatePoppingUpToStartDestination

@Composable
fun rememberEnergyProviderAppState(
    navController: NavHostController = rememberNavController()
): EnergyProviderAppState = remember(navController) {
    EnergyProviderAppState(navController)
}

class EnergyProviderAppState(
    val navController: NavHostController,
) {
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun onNavItemClick(navFeature: NavFeature) {
        navController.navigatePoppingUpToStartDestination(navFeature.route)
    }
}