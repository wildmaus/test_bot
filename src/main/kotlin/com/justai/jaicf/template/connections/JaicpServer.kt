package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.jaicp.JaicpServer
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.testBot

fun main() {
    JaicpServer(
        botApi = testBot,
        accessToken = accessToken,
        channels = listOf(
            TelegramChannel
        )
    ).start(wait = true)
}