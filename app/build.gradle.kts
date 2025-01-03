plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)


            alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.navigation)


    //   id ("kotlin-kapt")

      id ("kotlin-parcelize")


}






android {
    namespace = "com.example.digikalax"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.digikalax"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }



    buildFeatures {
        viewBinding = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)



    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.data.store)  // برای استفاده از Data Store
    implementation(libs.data.store.preferences)  // برای استفاده از Preferences Data Store


    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)


    implementation(libs.navigation.fragment)

    implementation(libs.navigation.ui)

    implementation(libs.lottie)

    implementation(libs.coil)

    implementation(libs.swipe)

    implementation(libs.okHttp)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    implementation(libs.gson)

    implementation(libs.calligraphy)
    implementation(libs.viewpump)


    implementation(libs.dynamicSizes)



    implementation(libs.pagingg)





   implementation(libs.mpAndroidChart)

//    //Android
//    implementation ("androidx.core:core-ktx:1.12.0")
//    implementation ("androidx.appcompat:appcompat:1.6.1")
//    implementation ("com.google.android.material:material:1.10.0")
//    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
//    testImplementation ("junit:junit:4.13.2")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
//    //Navigation
//    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
//    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")
//    //Dagger - Hilt
//    implementation ("com.google.dagger:hilt-android:2.51")
//    kapt ("com.google.dagger:hilt-compiler:2.51")
//    //Retrofit
//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//    //OkHTTP client
//    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
//    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
//    //Coroutines
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
//    //Lifecycle
//    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
//    //Room components
//    implementation ("androidx.room:room-runtime:2.6.1")
//    kapt ("androidx.room:room-compiler:2.6.1")
//    implementation ("androidx.room:room-ktx:2.6.1")
//    //Image Loading
//    implementation ("io.coil-kt:coil:2.5.0")
//    //Gson
//    implementation ("com.google.code.gson:gson:2.9.1")
//    //Calligraphy
//    implementation ("io.github.inflationx:calligraphy3:3.1.1")
//    implementation ("io.github.inflationx:viewpump:2.0.3")
//    //Other
//    implementation ("com.github.MrNouri:DynamicSizes:1.0")
//    implementation ("com.github.MatteoBattilana:WeatherView:3.0.0")
//    implementation ("com.airbnb.android:lottie:5.2.0")
}




