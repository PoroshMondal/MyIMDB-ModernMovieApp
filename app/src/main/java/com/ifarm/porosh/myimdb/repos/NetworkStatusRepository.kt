package com.ifarm.porosh.myimdb.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.ifarm.porosh.myimdb.components.NetworkStatusMonitor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkStatusRepository @Inject constructor(@ApplicationContext val context: Context) {

    val networkStatusMonitor = NetworkStatusMonitor(context)
    val connectionStatus: LiveData<Boolean> = networkStatusMonitor.connectionStatus

    fun startNetworkMonitoring() {
        Log.i("network_status", "In Repository: startNetworkMonitor")
        networkStatusMonitor.startNetworkMonitoring()
    }

    fun stopNetworkMonitoring(){
        Log.i("network_status", "In Repository: StopNetworkMonitor")
        networkStatusMonitor.stopNetworkMonitoring()
    }

}