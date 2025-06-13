        )
import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

package com.example.wifinotifier

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.NEARBY_WIFI_DEVICES)
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        ActivityCompat.requestPermissions(this, permissions.toTypedArray(), 0)

        val serviceIntent = Intent(this, WifiMonitorService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }

        val logTextView = findViewById<TextView>(R.id.logTextView)
        logTextView.text = LogRepository.getLogs().joinToString("\n")

        val handler = Handler(Looper.getMainLooper())
        val logUpdater = object : Runnable {
            override fun run() {
                logTextView.text = LogRepository.getLogs().joinToString("\n")
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(logUpdater)
    
    
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.any { it == android.content.pm.PackageManager.PERMISSION_DENIED }) {
            LogRepository.addLog("Permissão negada! Algumas funcionalidades podem não funcionar.")
        } else {
            LogRepository.addLog("Todas as permissões foram concedidas.")
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(logUpdater)
    }
    
}
