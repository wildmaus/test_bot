package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.reactions.buttons
import com.justai.jaicf.reactions.toState

val MainScenario = Scenario {

    append(context = "test", TestScenario)

    state("start") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            var name = context.client["name"]
            if (name == null) {
                request.telegram?.run {
                    name = message.chat.username
                }
            }
            reactions.run {
                say(
                    "Hi $name! How can I help you?"
                )
                go("/choose")
            }
        }
    }

    state("choose", modal = true) {
        action {
            reactions.run {
                buttons(
                    "My stats" toState "/myStats",
                    "Test" toState "/test/test",
                    "Top 5" toState "/top5",
                    "Joke" toState "/joke"
                )
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

    state("myStats") {
        activators {
            regex("/myStats")
        }

        action {
            reactions.run {
                say(
                    "You're awesome!"
                )
                go("/choose")
            }
        }
    }

    state("top5") {
        activators {
            regex("/top5")
        }

        action {
            reactions.run {
                say(
                    "You're 1 and that's all"
                )
                go("/choose")
            }
        }
    }

    state("joke") {
        activators {
            regex("/joke")
        }

        action {
            reactions.run {
                sayRandom(
                    "1st joke", "2nd joke", "more jokes"
                )
                go("/choose")
            }
        }
    }
}