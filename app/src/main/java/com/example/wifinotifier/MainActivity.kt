package com.example.wifinotifier

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var logTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissions = mutableListOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            permissions.add(android.Manifest.permission.NEARBY_WIFI_DEVICES)
        }
        val toRequest = permissions.filter {
            androidx.core.content.ContextCompat.checkSelfPermission(this, it) != android.content.pm.PackageManager.PERMISSION_GRANTED
        }
        if (toRequest.isNotEmpty()) {
            androidx.core.app.ActivityCompat.requestPermissions(this, toRequest.toTypedArray(), 1)
        }

        val intent = android.content.Intent(this, WifiMonitorService::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
        setContentView(R.layout.activity_main)

        logTextView = findViewById(R.id.logTextView)

        updateLogDisplay()
    }

    private fun updateLogDisplay() {
        val logs = LogRepository.getLogs().joinToString("\n")
        logTextView.text = logs
    }

    override fun onResume() {
        super.onResume()
        updateLogDisplay()
    }
}
