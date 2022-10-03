package com.bakabool.firstkmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform