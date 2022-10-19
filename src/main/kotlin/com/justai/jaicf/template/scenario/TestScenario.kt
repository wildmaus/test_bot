package com.justai.jaicf.template.scenario

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
            val testController = TestController(context)
            if (testController.getQuestionNumber() > 1) {
                reactions.run {
                    say(
                        "You have not finished session, continue?"
                    )
                    buttons(
                        "yes" toState "/test/questions", "no" toState "/test/restart"
                    )
                }
            } else {
                reactions.go("/test/restart")
            }
        }
    }

    state("questions", modal = true) {
        action {
            val testController = TestController(context)
            reactions.run {
                telegram?.say(
                    "Question ${testController.getQuestionNumber()}/20"
                )
                var options = testController.getOptions()
                buttons(
                    options[0] toState "check",
                    options[1] toState "check",
                    options[2] toState "check",
                    options[3] toState "check"
                )
            }
        }

        state("check") {
            action {
                val testController = TestController(context)
                if (testController.getAnswer() == request.input) testController.addScore()
                if (testController.getQuestionNumber() < 20) {
                    testController.nextQuestion()
                    reactions.go("..")
                } else {
                    reactions.go("result")
                }
            }

            state("result") {
                action {
                    val testController = TestController(context)
                    reactions.run {
                        say("And result is ${testController.getTotalScore()}")
                        go("/choose")
                    }
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

    state("restart") {
        action {
            val testController = TestController(context)
            testController.restart()
            reactions.go("/test/questions")
        }
    }
}