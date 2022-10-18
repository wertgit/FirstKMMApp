package com.bakabool.firstkmmapp

import com.bakabool.firstkmmapp.NewYear.daysUntilNewYear
import com.bakabool.findtimeshared.ShareClass

class Greeting {
    private val shareClass  = ShareClass()
    private val platform = getPlatform()

    fun greeting(): String {
        return "Hello, ${platform.name.reversed()}!" + "\nThere are only ${daysUntilNewYear()} days left until New Year! 🎅🏼 "
    }
}