package com.akole.energyproviderapp.domain.datastores

import com.akole.energyproviderapp.domain.models.EnergyLiveData

interface EMSListener {
    fun onData(data: EnergyLiveData)
}