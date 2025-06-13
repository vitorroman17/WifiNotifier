package com.example.wifinotifier

import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testBuildPasses() {
        assertTrue(true)
    }

    @Test
    fun testAddLog() {
        LogRepository.clear()
        LogRepository.addLog("Teste de log")
        val logs = LogRepository.getLogs()
        assertTrue("Deve conter o log adicionado", logs.contains("Teste de log"))
    }
}
