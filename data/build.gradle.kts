plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {

    implementation(project(":domain"))


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Okhttp
    implementation(libs.okhttp)
    testImplementation(libs.junit)
}