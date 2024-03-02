plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.zobaze.zobazerefractortask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zobaze.zobazerefractortask"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    signingConfigs {
        create("release") {
            keyAlias = project.properties["KEY_ALIAS"].toString()
            keyPassword = project.properties["kEY_PASSWORD"].toString()
            //storeFile = file("../../key")
            /*this we can uncomment it and directly build the app
            from system directly also good for ci/cd pipeline */
            storePassword = project.properties["STORE_PASSWORD"].toString()
        }
    }


    flavorDimensions += listOf("default")
    productFlavors {
        create("development") {
            dimension = "default"
            applicationId = "com.zobaze.zobazerefractortask.dev"
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("String", "BASE_URL", project.properties["BASE_URL_DEV"].toString())
        }
        create("production") {
            dimension = "default"
            applicationId = "com.zobaze.zobazerefractortask"
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("String", "BASE_URL", project.properties["BASE_URL_PROD"].toString())
        }
    }

    buildFeatures {
        buildConfig = true
        dataBinding = true
    }

}

dependencies {


    //base
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //LIFE CYCLE VIEW MODEL WITH LIVE DATA
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    implementation("androidx.lifecycle:lifecycle-process:2.6.2")


    //swipe refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

