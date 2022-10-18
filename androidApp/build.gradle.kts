
plugins {
    id(androidApp)
    kotlin(androidPlugin)
}

android {
    namespace = "com.bakabool.firstkmmapp.android"
    compileSdk = Versions.compile_sdk
    defaultConfig {
        applicationId = "com.bakabool.firstkmmapp.android"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        dataBinding  = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":findtimeshared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    with(Deps) {
        implementation(napier)
        implementation(material)
    }
    with(Compose) {
//        implementation(compiler)
//        implementation(runtime)
//        implementation(runtime_livedata)
      //  implementation(ui)
      //  implementation(tooling)
//        implementation(foundation)
//        implementation(foundationLayout)
//        implementation(material)
//        implementation(material_icons)
//        implementation(activity)
    }
}