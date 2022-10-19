package com.justai.jaicf.template

data class Top(val username: String, val score: Int)

var top5: MutableList<Top> = mutableListOf()