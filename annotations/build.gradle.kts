plugins { kotlin("multiplatform") }

kotlin {
    jvm()
    js()
    linuxX64()
    linuxArm64()
    mingwX64()
}
