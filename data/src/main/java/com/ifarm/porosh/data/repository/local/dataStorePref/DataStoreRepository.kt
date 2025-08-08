package com.ifarm.porosh.data.repository.local.dataStorePref

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setDataStoredInfo(isStored: Boolean)
    fun getIsDataStored(): Flow<Boolean>
}