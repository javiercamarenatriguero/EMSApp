package com.akole.energyproviderapp.domain.di
import com.akole.energyproviderapp.domain.datasources.EMSDataSource
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
        emsDataSource: EMSDataSource
    ): StartLiveDataConnection = StartLiveDataConnection(emsDataSource)

    @Provides
    fun providesStopLiveDataConnection(
        emsDataSource: EMSDataSource
    ): StopLiveDataConnection = StopLiveDataConnection(emsDataSource)


    @Provides
    fun providesGetHistoricalData(
        emsDataSource: EMSDataSource
    ): GetHistoricalData = GetHistoricalData(emsDataSource)
}