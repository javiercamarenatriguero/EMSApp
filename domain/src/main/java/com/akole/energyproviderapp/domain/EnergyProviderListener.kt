package com.akole.energyproviderapp.domain

import com.akole.energyproviderapp.domain.models.EnergyLiveData

interface EnergyProviderListener {
    fun onData(data: EnergyLiveData)
}