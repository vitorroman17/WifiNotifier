package com.example.wifinotifier


  Repositório de logs em memória.
 Armazena até 1000 entradas com timestamp.
 
object LogRepository {
    private val logList = mutableListOf<String>()

    
      Adiciona uma nova entrada de log com timestamp.
     
    fun addLog(message: String) {
        val timestamp = System.currentTimeMillis()
        logList.add("$timestamp: $message")
        if (logList.size > 1000) {
            logList.removeAt(0) // remove o log mais antigo
        }
    }

    
      Retorna todos os logs atuais.
     
    fun getLogs(): List<String> = logList.toList()

    
      Limpa todos os logs.
     
    // @Suppress("unused")
    fun clear() {
        logList.clear()
    }
}
