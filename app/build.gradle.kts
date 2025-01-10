plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.cropcare"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cropcare"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation(libs.volley)// Volley library for networking
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.material.v130alpha03)
    implementation(libs.material.v1110)
    implementation(libs.firebase.storage)
    implementation(libs.recyclerview)
    implementation(libs.firebase.database)


    //ans s3
    //implementation(libs.core)
    implementation(libs.activity.ktx)  // Ensure you have the right version of the Activity library
    implementation(libs.activity.compose)
    //recyclerview in subscribers fragment
    implementation(libs.recyclerview.v7)







    /* testImplementation(libs.junit)
     implementation(libs.firebase.database.v2020) // Use latest version
     implementation(libs.firebase.bom)
     implementation(libs.firebase.analytics)*/
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
