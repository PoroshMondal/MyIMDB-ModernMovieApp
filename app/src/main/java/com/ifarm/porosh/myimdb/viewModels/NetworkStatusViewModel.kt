package com.ifarm.porosh.myimdb.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ifarm.porosh.myimdb.repos.NetworkStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkStatusViewModel @Inject constructor(val repository: NetworkStatusRepository): ViewModel() {

    val connectionStatus: LiveData<Boolean> = repository.connectionStatus

    fun startNetworkMonitoring() {
        Log.i("network_status", "In ViewModel: startNetworkMonitor")
        repository.startNetworkMonitoring()
    }

    fun stopNetworkMonitoring(){
        Log.i("network_status", "In ViewModel: StopNetworkMonitor")
        repository.stopNetworkMonitoring()
    }

}