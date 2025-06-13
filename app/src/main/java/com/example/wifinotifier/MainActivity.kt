package com.example.wifinotifier

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var logTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
