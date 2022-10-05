package com.bakabool.firstkmmapp

import com.bakabool.firstkmmapp.NewYear.daysUntilNewYear
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Hello, ${platform.name.reversed()}!" + "\nThere are only ${daysUntilNewYear()} days left until New Year! 🎅🏼 "
    }
}