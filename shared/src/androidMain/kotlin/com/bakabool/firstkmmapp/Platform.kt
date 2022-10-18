package com.bakabool.firstkmmapp

import com.bakabool.findtimeshared.ShareClass

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    private val shareClass  = ShareClass()
}

actual fun getPlatform(): Platform = AndroidPlatform()