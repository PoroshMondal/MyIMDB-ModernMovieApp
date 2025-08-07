plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.safeArgsPlugin)
}

android {
    namespace = "com.ifarm.porosh.myimdb"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ifarm.porosh.myimdb"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // dagger hilt for DI
    implementation (libs.dagger.hilt.android)
    implementation(libs.androidx.annotation)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    kapt (libs.hilt.compiler)

    // Retrofit2
    implementation(libs.squareup.retrofit)
    implementation(libs.retrofit2.converter.moshi)

    // Moshi
    implementation(libs.squareup.moshi.kotlin)

    // Glide
    implementation(libs.glide)
    annotationProcessor (libs.glide.compiler)

    // loader
    implementation(libs.progress.dialog)

    // Room db
    implementation(libs.androidx.room.runtime)
    implementation (libs.androidx.work.rxjava2)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // DataStore
    implementation(libs.androidx.datastore)

    implementation(libs.androidx.multidex)
    implementation(libs.androidx.splash.screen)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}