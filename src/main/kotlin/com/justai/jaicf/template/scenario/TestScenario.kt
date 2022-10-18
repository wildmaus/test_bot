package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.reactions.buttons
import com.justai.jaicf.reactions.toState


val TestScenario = Scenario {
    var totalScore = 0;
    var questionNum = 1;
    state("test") {
        activators {

        }
        action {
            totalScore = 0
            questionNum = 1
            reactions.run {
                say(
                    "Let's check your kotlin level..."
                )
                go("/test/questions")
            }
        }
    }

    state("questions", modal = true) {
        action {
            reactions.run {
                telegram?.say(
                    "Question $questionNum/20"
                )
                buttons(
                    "option1" toState "check",
                    "option2" toState "check",
                    "option3" toState "check",
                    "option4" toState "check"
                )
            }
        }

        state("check") {
            action {

                var answer = request.input
                if (answer == "option1") totalScore++
                questionNum++
                if (questionNum < 6) {
                    reactions.go("..")
                }
                else {
                    reactions.go("result")
                }
            }

            state("result") {
                action {
                    reactions.run {
                        say("And result is $totalScore")
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
}