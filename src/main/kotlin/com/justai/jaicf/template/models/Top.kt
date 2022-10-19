package com.justai.jaicf.template.models

data class Top(val username: String, val score: Int)

var top5: MutableList<Top> = mutableListOf()