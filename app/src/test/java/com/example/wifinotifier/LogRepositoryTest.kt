package com.example.wifinotifier

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LogRepositoryTest {

    @Before
    fun setup() {
        LogRepository.clear()
    }

    @Test
    fun testAddAndGetLogs() {
        LogRepository.addLog("Evento A")
        LogRepository.addLog("Evento B")

        val logs = LogRepository.getLogs()
        assertEquals(2, logs.size)
        assertTrue(logs[0].contains("Evento A"))
        assertTrue(logs[1].contains("Evento B"))
    }
}
