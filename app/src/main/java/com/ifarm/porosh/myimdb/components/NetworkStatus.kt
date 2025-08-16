package com.ifarm.porosh.myimdb.components

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ifarm.porosh.myimdb.utilities.OtherUtil

class NetworkStatus(val mActivity: Activity): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val isConnected = OtherUtil.NetworkUtils.isConnected(activity = mActivity)

    }

}