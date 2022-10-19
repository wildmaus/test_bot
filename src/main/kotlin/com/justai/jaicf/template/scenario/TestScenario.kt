package com.justai.jaicf.template.scenario

import com.github.kotlintelegrambot.entities.ParseMode
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.reactions.buttons
import com.justai.jaicf.reactions.toState
import com.justai.jaicf.template.TestController


val TestScenario = Scenario {
    state("test") {
        activators {
            regex("/test")
        }
        action {
            reactions.run {
                say("Let's check your Kotlin level ...")
                reactions.go("/test/restart")
            }
        }
    }

    state("questions", modal = true) {
        action {
            val testController = TestController(context)
            reactions.run {
                telegram?.say(
                    "Question ${testController.getQuestionNumber() + 1}/20 \n"
                            + testController.getQuestion() + "\n\n"
                            + "1. ${testController.getOption(0)}\n"
                            + "2. ${testController.getOption(1)}\n"
                            + "3. ${testController.getOption(2)}\n"
                            + "4. ${testController.getOption(3)}",
                    parseMode = ParseMode.MARKDOWN
                )
                telegram?.buttons(
                    "1" toState "check",
                    "2" toState "check",
                    "3" toState "check",
                    "4" toState "check"
                )
            }
        }

        state("check") {
            action {
                val testController = TestController(context)
                if (testController.getAnswer() == request.input) testController.addScore()
                if (testController.getQuestionNumber() < 5) {
                    testController.nextQuestion()
                    reactions.go("..")
                } else {
                    reactions.go("result")
                }
            }

            state("result") {
                action {
                    val testController = TestController(context)
                    val score = testController.getTotalScore()
                    val msg: String
                    val lvl: String
                    if (score <= 5) {
                        msg = "Oh... you scored very little, you should learn the language better!"
                        lvl = "Beginner"
                    } else if (score <= 10) {
                        msg = "You know the basic principles, but you still have work to do!"
                        lvl = "Middle"
                    } else if (score <= 15) {
                        msg = "You know the language at a sufficient level, not many people know it better!"
                        lvl = "Pro"
                    } else {
                        msg = "You're just a Kotlin god, why are you even passing this test?!"
                        lvl = "God"
                    }
                    testController.storeResult(lvl)
                    reactions.run {
                        telegram?.say(
                            "Your score is ${score}/20!\n" + "Level: *${lvl}*\n" + msg,
                            parseMode = ParseMode.MARKDOWN
                        )
                        go("/choose")
                    }
                }
            }
        }

        state("ClickButtons") {
            activators {
                catchAll()
            }

            action {
                reactions.run {
                    say("Please click the button")
                    go("..")
                }
            }
        }
    }

    state("restart") {
        action {
            val testController = TestController(context)
            testController.restart()
            reactions.go("/test/questions")
        }
    }
}