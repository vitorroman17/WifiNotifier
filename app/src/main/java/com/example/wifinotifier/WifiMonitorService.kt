package com.example.wifinotifier

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WifiMonitorService : Service() {

    override fun onCreate() {
        super.onCreate()

        val notification = NotificationUtils.createForegroundNotification(this)
        startForeground(1, notification)

                val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(WifiReceiver(), filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // código já existente
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
