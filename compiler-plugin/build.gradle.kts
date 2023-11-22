plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compileOnly(libs.compiler.embeddable)
    compileOnly(libs.auto.service)
    kapt(libs.auto.service)
}