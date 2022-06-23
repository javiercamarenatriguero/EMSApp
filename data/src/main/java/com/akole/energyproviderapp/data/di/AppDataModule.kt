package com.akole.energyproviderapp.data.di
import com.akole.energyproviderapp.data.datasources.LocalEnergyHistoricalDataDatastore
import com.akole.energyproviderapp.data.datasources.SensorLiveDataStore
import com.akole.energyproviderapp.domain.datastores.EnergyHistoricalDataStore
import com.akole.energyproviderapp.domain.datastores.EnergyLiveDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    fun providesEnergyHistoricalDataStore(): EnergyHistoricalDataStore =
        LocalEnergyHistoricalDataDatastore()

    @Provides
    fun providesEnergyLiveDataStore(): EnergyLiveDataStore =
        SensorLiveDataStore()
}