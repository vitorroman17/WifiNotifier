package com.example.wifinotifier

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.IBinder

class WifiMonitorService : Service() {

    private val wifiReceiver = WifiReceiver()

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(wifiReceiver, filter)
        val notification = NotificationUtils.createForegroundNotification(this)
        startForeground(1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
