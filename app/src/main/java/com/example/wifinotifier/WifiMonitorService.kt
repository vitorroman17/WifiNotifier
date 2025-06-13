package com.example.wifinotifier

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder

class WifiMonitorService : Service() {

    private lateinit var wifiReceiver: BroadcastReceiver

    override fun onCreate() {
        super.onCreate()

        wifiReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                context ?: return

                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val rawSsid = wifiManager.connectionInfo?.ssid
                val ssid = rawSsid?.removePrefix("\"")?.removeSuffix("\"") ?: "<unknown>"

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val network = connectivityManager.activeNetwork
                    val capabilities = connectivityManager.getNetworkCapabilities(network)

                    if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                        LogRepository.addLog("Wi-Fi conectado: $ssid")
                        NotificationUtils.showNotification(context, ssid, "")
                    } else {
                        LogRepository.addLog("Wi-Fi desconectado")
                        NotificationUtils.showNotification(context, "Wi-Fi Desconectado", "")
                    }
                } else {
                    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                    if (networkInfo?.isConnected == true && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                        LogRepository.addLog("Wi-Fi conectado: $ssid")
                        NotificationUtils.showNotification(context, ssid, "")
                    } else {
                        LogRepository.addLog("Wi-Fi desconectado")
                        NotificationUtils.showNotification(context, "Wi-Fi Desconectado", "")
                    }
                }
            }
        }

        val filter = IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, filter)
        LogRepository.addLog("WifiMonitorService iniciado")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
        LogRepository.addLog("WifiMonitorService encerrado")
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
