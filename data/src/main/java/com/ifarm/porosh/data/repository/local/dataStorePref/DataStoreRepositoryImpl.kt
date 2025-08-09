package com.ifarm.porosh.data.repository.local.dataStorePref

import android.content.Context
import com.ifarm.porosh.data.local.DataStoreSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor( val context: Context) : DataStoreRepository {
    private val dataStoreSettings: DataStoreSettings = DataStoreSettings(context)

    override suspend fun setDataStoredInfo(isStored: Boolean) {
        dataStoreSettings.setDataStoredInfo(isStored)
    }

    override fun getIsDataStored(): Flow<Boolean> {
        return dataStoreSettings.getIsDataStored()
    }

}
