/**
 * This file will contain all the variables you’ll
need to define all your plugins and dependencies
The reason for creating this
file is to avoid having to maintain all the versions for your plugins and
dependencies in many different files. Defining the versions in one place makes it
easy to change later.

 */

const val androidPlugin = "android"
const val androidApp = "com.android.application"
const val androidLib = "com.android.library"
const val multiplatform = "multiplatform"
const val composePlugin = "org.jetbrains.compose"
const val cocopods = "native.cocoapods"

object Versions {
    const val min_sdk = 26
    const val target_sdk = 33
    const val compile_sdk = 32

    // Plugins
    const val kotlin = "1.6.10"
    const val kotlin_gradle_plugin = "1.6.10"
    const val android_gradle_plugin = "7.0.4"
    const val desktop_compose_plugin = "1.0.1"
    const val compose_compiler_version = "1.1.0-rc02"
    const val compose_version = "1.2.1"
    const val coroutines = "1.5.0-native-mt"
    const val junit = "4.13.2"
    const val material = "1.4.0"
    const val kotlinxDateTime = "0.3.1"
    const val activity_compose = "1.4.0"
    const val napier = "2.1.0"
    const val junit5 = "1.5.10"
    const val frameworkName = "shared"

}

// This defines variables for the plugins and for some useful libraries.
object Deps {
    const val android_gradle_plugin =
        "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlingradle-plugin:${Versions.kotlin_gradle_plugin}"


    const val junit = "junit:junit:${Versions.junit}"
    const val material =
        "com.google.android.material:material:${Versions.material}"
    const val napier = "io.github.aakira:napier:${Versions.napier}"

    /**
     * These are a few libraries from JetBrains — the developers of KMP — for
    multiplatform datetime handling, as well as Desktop Compose
     */
    object JetBrains {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
        const val uiDesktop = "org.jetbrains.compose.ui:uidesktop:${Versions.desktop_compose_plugin}"
        const val uiUtil = "org.jetbrains.compose.ui:uiutil:${Versions.desktop_compose_plugin}"
    }

}


object Compose {
    const val ui =
        "androidx.compose.ui:ui:${Versions.compose_version}"
    const val uiUtil = "androidx.compose.ui:uiutil:${Versions.compose_version}"
    const val tooling = "androidx.compose.ui:uitooling:${Versions.compose_version}"
    const val foundation =
        "androidx.compose.foundation:foundation:${Versions.compose_version}"
    const val material =
        "androidx.compose.material:material:${Versions.compose_version}"
    const val material_icons =
        "androidx.compose.material:materialicons-extended:${Versions.compose_version}"
    const val runtime =
        "androidx.compose.runtime:runtime:${Versions.compose_version}"
    const val compiler =
        "androidx.compose.compiler:compiler:${Versions.compose_version}"
    const val runtime_livedata =
        "androidx.compose.runtime:runtimelivedata:${Versions.compose_version}"
    const val foundationLayout =
        "androidx.compose.foundation:foundationlayout:${Versions.compose_version}"
    const val activity = "androidx.activity:activitycompose:${Versions.activity_compose}"
}


object Coroutines {
    const val common = "org.jetbrains.kotlinx:kotlinx-coroutinescore:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutinesandroid:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutinestest:${Versions.coroutines}"
}





