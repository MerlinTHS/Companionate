plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compileOnly(libs.compiler)
    compileOnly(libs.auto.service)
    kapt(libs.auto.service)

    testImplementation(libs.compiler)
    testImplementation(testLibs.internal.framework)

    testImplementation(platform("org.junit:junit-bom:5.8.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-commons")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.platform:junit-platform-runner")
    testImplementation("org.junit.platform:junit-platform-suite-api")

    testRuntimeOnly("org.jetbrains.kotlin:kotlin-test:1.9.0")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-script-runtime:1.9.0")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-annotations-jvm:1.9.0")

    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    testImplementation("org.jetbrains.kotlin:kotlin-compiler-internal-test-framework:1.9.0")
    testImplementation("junit:junit:4.13.2")
}

sourceSets.test {
    java.setSrcDirs(listOf("test", "test-gen"))
    resources.setSrcDirs(listOf("testResources"))
}

tasks.create<JavaExec>("generateTests") {
    classpath = sourceSets.test.get().runtimeClasspath
    mainClass.set("io.mths.companionate.GenerateTestsKt")
}

tasks.test {
    useJUnitPlatform()
    doFirst {
        setLibraryProperty("org.jetbrains.kotlin.test.kotlin-stdlib", "kotlin-stdlib")
        setLibraryProperty("org.jetbrains.kotlin.test.kotlin-stdlib-jdk8", "kotlin-stdlib-jdk8")
        setLibraryProperty("org.jetbrains.kotlin.test.kotlin-reflect", "kotlin-reflect")
        setLibraryProperty("org.jetbrains.kotlin.test.kotlin-test", "kotlin-test")
        setLibraryProperty("org.jetbrains.kotlin.test.kotlin-script-runtime", "kotlin-script-runtime")
        setLibraryProperty("org.jetbrains.kotlin.test.kotlin-annotations-jvm", "kotlin-annotations-jvm")
    }
}

fun Test.setLibraryProperty(propName: String, jarName: String) {
    val path = project.configurations
        .testRuntimeClasspath.get()
        .files
        .find { """$jarName-\d.*jar""".toRegex().matches(it.name) }
        ?.absolutePath
        ?: return
    systemProperty(propName, path)
}
