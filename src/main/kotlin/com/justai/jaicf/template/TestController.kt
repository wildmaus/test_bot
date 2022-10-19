package com.justai.jaicf.template

import com.justai.jaicf.context.BotContext

class TestController(context: BotContext) {
    private var totalScore: Int? by context.client
    private var currentQuestion: Int? by context.client
    private var question: Question by context.session

    fun getQuestionNumber(): Int {
        return currentQuestion ?: 1
    }

    fun getTotalScore(): Int {
        return totalScore ?: 0
    }

    fun getOptions(): List<String> {
        return question.options.shuffled()
    }

    fun getAnswer(): String {
        return question.options[question.answer]
    }

    fun addScore() {
        val score = totalScore ?: 0
        totalScore = score + 1
    }

    fun nextQuestion() {
        val num = currentQuestion ?: 1
        currentQuestion = num + 1
        updateQuestion()
    }

    fun restart() {
        totalScore = 0
        currentQuestion = 1
        updateQuestion()
    }

    private fun updateQuestion() {
        question = Question(currentQuestion ?: 1, 0, listOf("option1", "option2", "option3", "option4"))
    }
}