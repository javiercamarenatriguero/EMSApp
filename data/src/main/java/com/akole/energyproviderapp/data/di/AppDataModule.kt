package com.akole.energyproviderapp.data.di
import com.akole.energyproviderapp.data.datasources.QuasarChargerDataStore
import com.akole.energyproviderapp.domain.datastores.EMSDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    fun providesEMSDataStore(): EMSDataStore =
        QuasarChargerDataStore()
}