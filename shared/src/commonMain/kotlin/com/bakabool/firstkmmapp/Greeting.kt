package com.bakabool.firstkmmapp

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Sup, ${platform.name}!"
    }
}