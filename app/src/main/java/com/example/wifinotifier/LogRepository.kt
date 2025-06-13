package com.example.wifinotifier

object LogRepository {

    private val logList = mutableListOf<String>()

    fun addLog(message: String) {
        logList.add("${System.currentTimeMillis()}: $message")
        if (logList.size > 1000) {
            logList.removeAt(0) // Compatível com todas as APIs
        }
    }

    fun getLogs(): List<String> {
        return logList.toList() // Retorna cópia segura
    }
}
