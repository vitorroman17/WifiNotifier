package com.example.wifinotifier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class WifiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        val isWifi = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true

        if (isWifi) {
            val ssid = context.getSystemService(Context.WIFI_SERVICE)
                .let { it as android.net.wifi.WifiManager }
                .connectionInfo.ssid
            NotificationUtils.showNotification(context, "Wi-Fi conectado", "SSID: $ssid")
            LogRepository.addLog("Conectado ao Wi-Fi: $ssid")
        } else {
            NotificationUtils.showNotification(context, "Wi-Fi desconectado", "Sem conexão com rede Wi-Fi")
            LogRepository.addLog("Wi-Fi desconectado")
        }
    }
}
