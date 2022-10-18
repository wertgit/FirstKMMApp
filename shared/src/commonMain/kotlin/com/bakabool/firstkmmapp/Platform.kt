package com.bakabool.firstkmmapp

interface Platform {
    val name: String
}

/**
 * This says that you expect each
platform to have an actual class named Platform that implements this class and
has a variable named platform that is a string
 */
expect fun getPlatform(): Platform