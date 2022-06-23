package com.akole.energyproviderapp.domain.di
import com.akole.energyproviderapp.domain.datastores.EnergyHistoricalDataStore
import com.akole.energyproviderapp.domain.usecases.GetHistoricalData
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDomainModule {

    @Provides
    fun providesGetHistoricalData(
        energyHistoricalDataStore: EnergyHistoricalDataStore
    ): GetHistoricalData = GetHistoricalData(energyHistoricalDataStore)
}