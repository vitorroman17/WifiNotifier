package com.example.wifinotifier

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WifiMonitorService : Service() {

    override fun onCreate() {
        super.onCreate()

        val notification = NotificationUtils.createForegroundNotification(this)
        startForeground(1, notification)

        // Inicie o monitoramento do Wi-Fi aqui
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // código já existente
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
