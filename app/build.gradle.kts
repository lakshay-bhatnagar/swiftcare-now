plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.swiftcare"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.swiftcare"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources.excludes.add("META-INF/DEPENDENCIES")
    }
}

dependencies {
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.activity:activity:1.8.2")
    implementation("com.twilio.sdk:twilio:9.3.0")
    implementation("com.airbnb.android:lottie:6.3.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
