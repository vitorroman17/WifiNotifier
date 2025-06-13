package com.example.wifinotifier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log

class WifiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val isWifi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cm = context.getSystemService(ConnectivityManager::class.java)
            val network = cm?.activeNetwork
            val capabilities = cm?.getNetworkCapabilities(network)
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
        } else {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info: NetworkInfo? = cm.activeNetworkInfo
            info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
        }

        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? android.net.wifi.WifiManager
        val ssid = wifiManager?.connectionInfo?.ssid ?: "SSID desconhecido"

        if (isWifi) {
            NotificationUtils.showNotification(context, "Wi-Fi conectado", ssid)
            LogRepository.addLog("Conectado ao Wi-Fi: $ssid")
        } else {
            NotificationUtils.showNotification(context, "Wi-Fi desconectado", "Sem conexão com rede Wi-Fi")
            LogRepository.addLog("Wi-Fi desconectado")
        }
    }
}
