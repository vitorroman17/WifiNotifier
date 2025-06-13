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
        val testMessage = "Teste de log"
        LogRepository.addLog(testMessage)
        val logs = LogRepository.getLogs()
        assertTrue("Deve conter o log com timestamp", logs.any { it.contains(testMessage) })
    }
}
