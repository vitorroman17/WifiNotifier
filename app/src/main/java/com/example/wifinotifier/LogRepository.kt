package com.example.wifinotifier

object LogRepository {
    private val logList = mutableListOf<String>()

    fun addLog(message: String) {
        logList.add("${System.currentTimeMillis()}: $message")
        if (logList.size > 1000) logList.removeAt(0)
    }

    fun getLogs(): List<String> = logList.toList()

    fun clear() {
        logList.clear()
    }
}
