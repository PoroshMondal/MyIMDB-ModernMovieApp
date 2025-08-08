package com.ifarm.porosh.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ifarm.porosh.data.DataConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class DataStoreSettings(private val context: Context) {

    companion object {
        private var mInstance: DataStoreSettings? = null

        fun getSettingsInstance(context: Context): DataStoreSettings? {
            return mInstance ?: synchronized(this){
                mInstance = DataStoreSettings(context)
                mInstance
            }
        }

    }

    object PreferencesKeys{
        val dataStored = booleanPreferencesKey(DataConstants.KEY_PREF_IS_EXISTS)
    }

    suspend fun setDataStoredInfo(isStored: Boolean){
        context.dataStore.edit { preference ->
            preference[PreferencesKeys.dataStored] = isStored
        }
    }

    fun getIsDataStored(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.dataStored] ?: false
        }
    }

}