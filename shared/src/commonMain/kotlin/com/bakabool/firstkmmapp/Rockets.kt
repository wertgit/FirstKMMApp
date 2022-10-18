package com.bakabool.firstkmmapp

import com.bakabool.firstkmmapp.NewYear.daysUntilChristmas
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Rockets {

    /**
     * a Ktor HTTPClient instance to execute network requests and parse the resulting JSON
     * To deserialize the result of the GET request,
     * the ContentNegotiation Ktor plugin and the JSON serializer are used.
     */
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    /**
     * The suspend modifier in the greeting() function is necessary because it now contains a call to get().
     * It's a suspend function that has an asynchronous operation
     * to retrieve data over the internet and can only be called from within a coroutine or another suspend function.
     */
    suspend  fun rocketGreeting(): String {
        val rockets: List<RocketLaunch> =
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what! >"+
            "\n\nThere are only ${daysUntilChristmas()} days left until Christmas! 🎅🏼 " +
            "\n\nFor mission: ${lastSuccessLaunch.missionName} 🚀"+
            "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"
    }

    suspend fun fetchRockets(): List<RocketLaunch> {
        return httpClient.get("https://api.spacexdata.com/v4/launches").body()
    }
}