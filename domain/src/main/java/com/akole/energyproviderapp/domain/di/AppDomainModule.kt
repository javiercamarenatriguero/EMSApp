package com.akole.energyproviderapp.domain.di
import com.akole.energyproviderapp.domain.datastores.EMSDataStore
import com.akole.energyproviderapp.domain.usecases.GetHistoricalData
import com.akole.energyproviderapp.domain.usecases.StartLiveDataConnection
import com.akole.energyproviderapp.domain.usecases.StopLiveDataConnection
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDomainModule {

    @Provides
    fun providesStartLiveDataConnection(
        emsDataStore: EMSDataStore
    ): StartLiveDataConnection = StartLiveDataConnection(emsDataStore)

    @Provides
    fun providesStopLiveDataConnection(
        emsDataStore: EMSDataStore
    ): StopLiveDataConnection = StopLiveDataConnection(emsDataStore)


    @Provides
    fun providesGetHistoricalData(
        emsDataStore: EMSDataStore
    ): GetHistoricalData = GetHistoricalData(emsDataStore)
}