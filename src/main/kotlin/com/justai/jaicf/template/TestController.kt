package com.justai.jaicf.template

import com.justai.jaicf.context.BotContext

class TestController(context: BotContext) {
    private var totalScore: Int? by context.client
    private var currentQuestion: Int? by context.client
    private var results: MutableList<Int>? by context.client
    private var topLvl: String? by context.client
    private var name: String? by context.client

    fun getQuestionNumber(): Int {
        return currentQuestion ?: 0
    }

    fun getTotalScore(): Int {
        return totalScore ?: 0
    }

    fun getQuestion(): String {
        return questions[currentQuestion ?: 0].question
    }

    fun getOption(index: Int): String {
        return questions[currentQuestion ?: 0].options[index]
    }

    fun getAnswer(): String {
        return questions[currentQuestion ?: 0].answer
    }

    fun addScore() {
        val score = totalScore ?: 0
        totalScore = score + 1
    }

    fun nextQuestion() {
        val num = currentQuestion ?: 0
        currentQuestion = num + 1
    }

    fun storeResult(lvl: String) {
        if (results == null) {
            results = mutableListOf(totalScore ?: 0)
            topLvl = lvl
        } else {
            results!!.sortDescending()
            if (totalScore ?: 0 > results!![0]) {
                topLvl = lvl
            }
            results!!.add(totalScore ?: 0)
        }
        top5.add(Top(name ?: "username", totalScore ?: 0))
        top5.sortByDescending { it.score }
        if (top5.size > 5) {
            top5 = top5.dropLast(top5.size - 5).toMutableList()
        }
    }


    fun restart() {
        totalScore = 0
        currentQuestion = 0
    }
}