package com.example.wifinotifier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build

class WifiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val rawSsid = wifiManager.connectionInfo.ssid
        val ssid = rawSsid?.removePrefix("\"")?.removeSuffix("\"") ?: "<unknown>"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                LogRepository.addLog("Wi-Fi conectado: $ssid")
                NotificationUtils.showNotification(context, "Conectado: $ssid", "")
            } else {
                LogRepository.addLog("Wi-Fi desconectado")
                NotificationUtils.showNotification(context, "Wi-Fi Desconectado", "")
            }
        } else {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                LogRepository.addLog("Wi-Fi conectado: $ssid")
                NotificationUtils.showNotification(context, "Conectado: $ssid", "")
            } else {
                LogRepository.addLog("Wi-Fi desconectado")
                NotificationUtils.showNotification(context, "Wi-Fi Desconectado", "")
            }
        }
    }
}
