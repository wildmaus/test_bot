package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.testBot

fun main() {
    JaicpPollingConnector(
        testBot,
        accessToken,
        channels = listOf(
            TelegramChannel
        )
    ).runBlocking()
}