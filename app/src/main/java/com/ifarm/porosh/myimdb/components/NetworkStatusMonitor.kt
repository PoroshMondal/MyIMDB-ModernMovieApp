package com.ifarm.porosh.myimdb.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkStatusMonitor(val context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private val _connectionStatus = MutableLiveData<Boolean>()
    val connectionStatus: LiveData<Boolean> get() = _connectionStatus

    fun startNetworkMonitoring() {
        // Best approach: registerDefaultNetworkCallback
        if (networkCallback == null) {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Log.i("network_status", "On Network Class Is onAvailable: ${network.toString()}")
                    _connectionStatus.postValue(true)
                }

                override fun onLost(network: Network) {
                    Log.i("network_status", "On Network Class Is onLost: ${network.toString()}")
                    _connectionStatus.postValue(false)
                }
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback!!)
    }

    fun stopNetworkMonitoring() {
        networkCallback?.let {
            connectivityManager.unregisterNetworkCallback(it)
            networkCallback = null
        }
    }
}