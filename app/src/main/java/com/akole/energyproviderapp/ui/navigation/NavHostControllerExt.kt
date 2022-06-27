package com.akole.energyproviderapp.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigatePoppingUpToStartDestination(route: String) {
    if(!route.contains(this.currentDestination?.route ?: "")) {
        navigate(route) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}