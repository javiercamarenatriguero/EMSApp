package com.akole.energyproviderapp.domain

interface EnergyProvider {
    fun startService(listener: EnergyProviderListener)
    fun stopService()
}

