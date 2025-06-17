package com.example.wifinotifier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var logTextView: TextView

    private val logReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val log = intent?.getStringExtra("log") ?: return
            logTextView.append("$log\n")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logTextView = findViewById(R.id.logTextView)
        atualizarLogs()
    }

    private fun atualizarLogs() {
        val logs = LogRepository.getLogs().joinToString("\n")
        logTextView.text = logs
    }

    override fun onResume() {
        super.onResume()
        atualizarLogs()
        registerReceiver(logReceiver, IntentFilter("com.example.wifinotifier.LOG_EVENT"))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(logReceiver)
    }
}
