package com.azalia.angkot.utils

import java.sql.Date
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Halo"
                    else -> "error" }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("time") || message.contains("?")-> {
                val timeStamp = (System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp))

                date.toString()

            }

            //What Routes (1)
            /*message.contains("from pasar senen") && message.contains("to") && message.contains("kampung melayu") ||
                    message.contains("from RSPAD") && message.contains("to") && message.contains("kampung melayu") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("") ||
                    message.contains("from ") && message.contains("to") && message.contains("")-> {
                val timeStamp = (System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp))

                date.toString()

            }*/

            //What Routes (1)
            message.contains("from") && message.contains("to") -> {
                when {
                    message.contains("kampung melayu") && message.contains("pasar senen") -> {
                        handleRouteRequest(message, "kampung melayu", "pasar senen")
                    }
                    message.contains("kampung melayu") && message.contains("rspad") -> {
                        handleRouteRequest(message, "kampung melayu", "rspad")
                    }
                    message.contains("gang suzuki") && message.contains("sekolah cahaya sakti otista i") -> {
                        handleRouteRequest(message, "gang suzuki", "sekolah cahaya sakti otista i")
                    }
                    else -> {
                        "Please provide valid route information."
                    }
                }
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "Sorry, I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Please let me know where are you at and where do you want to go, maybe you can use this template: I'm from ... and I want to go to ..."
                    else -> "error"
                }
            }
        }
    }
    private fun handleRouteRequest(message: String, startLocation: String, endLocation: String): String {
        val endLocationSubstring: String = message.substringAfterLast("to", "").trim().toLowerCase()
        val startLocationSubstring: String = message.substringAfterLast("from", "").substringBefore("to").trim().toLowerCase()

        return try {
            val answer = Route1.routes(startLocationSubstring, endLocationSubstring)
            "$answer"
        } catch (e: Exception) {
            "Error processing the route request."
        }
    }
}