package com.justai.jaicf.template

import com.justai.jaicf.channel.http.ContentType.Companion.Json
import kotlinx.serialization.Serializable

@Serializable
data class Question(val id: Int = 0, val answer: Int = 0, val options: List<String> = listOf())
