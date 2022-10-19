package com.justai.jaicf.template.scenario

import com.github.kotlintelegrambot.entities.ParseMode
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.reactions.buttons
import com.justai.jaicf.reactions.toState
import com.justai.jaicf.template.top5

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
                    name = message.chat.firstName ?: message.chat.username
                    context.client["name"] = name
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
                if (context.client["results"] == null) {
                    say("You didn't pass the test yet")
                } else {
                    var result = context.client["results"] as MutableList<Int>
                    result.sortDescending()
                    val end = if (result.size > 5) 4 else result.size - 1
                    var msg: String = "Best results:\n"
                    for (i in 0..end) {
                        msg += "${i + 1}. Score: _${result[i]}_\n"
                    }
                    telegram?.say(
                        "Your level is *${context.client["topLvl"]}*\n" + msg,
                        parseMode = ParseMode.MARKDOWN
                    )
                }
                go("/choose")
            }
        }
    }

    state("top5") {
        activators {
            regex("/top5")
        }

        action {
            reactions.telegram?.run {
                var msg = "Top 5 users:\n"
                var i = 1
                for (item in top5) {
                    msg += "${i++}. ${item.username}, Score: _${item.score}_\n"
                }
                for (i in top5.size..4) {
                    msg += "${i + 1}.\n"
                }
                say(
                    msg,
                    parseMode = ParseMode.MARKDOWN
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