package com.akole.energyproviderapp.data.di
import com.akole.energyproviderapp.data.adapter.QuasarChargerAdapter
import com.akole.energyproviderapp.data.datasources.QuasarChargerDataSource
import com.akole.energyproviderapp.domain.datasources.EMSDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    @Singleton
    fun providesEMSDataStore(): EMSDataSource =
        QuasarChargerDataSource(QuasarChargerAdapter())

}
