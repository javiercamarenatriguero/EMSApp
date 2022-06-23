package com.akole.energyproviderapp.domain.datastores

import com.akole.energyproviderapp.domain.models.EnergyLiveData

interface EnergyProviderListener {
    fun onData(data: EnergyLiveData)
}