package com.example.wifinotifier

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.*
import android.os.IBinder
import android.util.Log

class WifiMonitorService : Service() {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationUtils.createForegroundNotification(this)
        startForeground(1, notification) // Notificação de serviço

        iniciarMonitoramento()
        return START_STICKY
    }

    private fun iniciarMonitoramento() {
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                val msg = "Wi-Fi disponível"
                NotificationUtils.showNotification(applicationContext, "Wi-Fi Conectado", msg)
                LogRepository.addLog(msg)
                Log.d("WifiMonitorService", msg)
                enviarBroadcast(msg)
            }

            override fun onLost(network: Network) {
                val msg = "Wi-Fi desconectado"
                NotificationUtils.showNotification(applicationContext, "Wi-Fi Desconectado", msg)
                LogRepository.addLog(msg)
                Log.d("WifiMonitorService", msg)
                enviarBroadcast(msg)
            }
        }

        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    private fun enviarBroadcast(msg: String) {
        val intent = Intent("com.example.wifinotifier.LOG_EVENT")
        intent.putExtra("log", msg)
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::connectivityManager.isInitialized && ::networkCallback.isInitialized) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
