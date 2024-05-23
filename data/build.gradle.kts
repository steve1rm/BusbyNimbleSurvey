plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization)
}

android {
    namespace = "me.androidbox.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        /* Retrieves API from local.properties */
        val properties = org.jetbrains.kotlin.konan.properties.Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "CLIENT_KEY", "\"${properties.getProperty("CLIENT_KEY")}\"")
        buildConfigField("String", "CLIENT_SECRET", "\"${properties.getProperty("CLIENT_SECRET")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.timber)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.ktor)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.ktor)
    implementation(libs.securityCrypto)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}