plugins {
    // The first plugin is for KMP and defines this module as a multiplatform module
    kotlin(multiplatform)
    // You’ll use this below to create an Android library for use in an Android app
    id(androidLib)
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version "1.7.20"
}

kotlin {
    // define an Android target
    android()

    // defines a target for the iOS different emulator and chip version you intent to support
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    val sqlDelightVersion: String by project
    val ktorVersion = "2.1.1"

    sourceSets {
        val commonMain by getting{
            // add Multiplatform dependencies. These are multiplatform libraries that support multiple targets
            dependencies {
                implementation(project(":findtimeshared"))
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.squareup.sqldelight:runtime:1.5.3")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.bakabool.firstkmmapp"
    compileSdk = Versions.compile_sdk
    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
    }
}


/**
 * SQLDelight also needs the last block to define the database name and the package/path where queries are defined.
 * https://blog.logrocket.com/building-cross-platform-mobile-apps-kotlin-multiplatform/
 */
sqldelight {
    database("KmmDB") {
        packageName = "com.bakabool.firstkmmapp.shared.cache"
    }
}