package com.justai.jaicf.template.scenario

import com.github.kotlintelegrambot.entities.ParseMode
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.reactions.buttons
import com.justai.jaicf.reactions.toState
import com.justai.jaicf.template.models.top5

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
            reactions.telegram?.run {
                image("https://ih1.redbubble.net/image.1581044512.7912/st,small,507x507-pad,600x600,f8f8f8.jpg")
                say(
                    "Hi *$name!* I'm simple bot for testing your _knowledge of Kotlin_. How can I help you?",
                    parseMode = ParseMode.MARKDOWN
                )
                go("/choose")
            }
        }
    }

    state("choose", modal = true) {
        activators {
            intent("choose")
        }
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

        state("bye") {
            activators {
                regex("/end")
                intent("Bye")
            }
            action {
                reactions.sayRandom(
                    "See you soon!",
                    "Bye-bye!"
                )
                reactions.image("https://i.pinimg.com/originals/08/e9/1c/08e91c41f605afe38c114149c690fee2.gif")
            }
        }
    }

    state("myStats") {
        activators {
            regex("/myStats")
            intent("stats")
        }

        action {
            reactions.run {
                if (context.client["results"] == null) {
                    say("You didn't pass the test yet")
                } else {
                    val result = context.client["results"] as MutableList<Int>
                    result.sortDescending()
                    val end = if (result.size > 5) 4 else result.size - 1
                    var msg = "Best results:\n"
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
            intent("top")
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
            intent("joke")
        }

        action {
            reactions.run {
                sayRandom(
                    "How many programmers does it take to change a light bulb?\nNone – It’s a hardware problem.",
                    "There are only 10 kinds of people in this world: those who know binary and those who don’t",
                    "Programmer is a machine that turns coffee into code."
                )
                go("/choose")
            }
        }
    }
}