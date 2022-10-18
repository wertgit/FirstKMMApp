plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    id("com.squareup.sqldelight").version("1.5.3").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    id("com.github.ben-manes.versions").version("0.42.0")
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
//    dependencies {
//        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
//    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
