package com.example.wifinotifier

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.IBinder

class WifiMonitorService : Service() {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onCreate() {
        super.onCreate()

        // Obter o ConnectivityManager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Criar notificação de foreground
        val notification = NotificationUtils.createForegroundNotification(this)
        startForeground(1, notification)

        // Criar e registrar NetworkCallback para monitorar Wi-Fi
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                NotificationUtils.showNotification(applicationContext, "Wi-Fi Conectado", "Rede disponível")
                LogRepository.addLog("Conectado ao Wi-Fi")
            }

            override fun onLost(network: Network) {
                NotificationUtils.showNotification(applicationContext, "Wi-Fi Desconectado", "Rede perdida")
                LogRepository.addLog("Wi-Fi desconectado")
            }
        }

        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancelar o registro do NetworkCallback para evitar vazamentos
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Garante que o serviço continue em segundo plano se o sistema o matar
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
