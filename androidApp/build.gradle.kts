plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "dev.haroldjose.simpleaudioplayerkmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "dev.haroldjose.simpleaudioplayerkmm.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
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
    val coroutinesVersion: String by project
    val koinVersion: String by project
    val koinAndroidVersion: String by project
    val koinAndroidComposeVersion: String by project

    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinAndroidVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinAndroidComposeVersion")
}