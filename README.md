<h1 align="center">Companionate</h1>

Companionate is a compiler plugin that allows you to add a companion object to a type annotated with `@Companionate`.
This annotation can also be used as a meta-annotation for those you use with your annotation processor.

## Tests

If you're getting ClassNotFoundException for `com/intellij/openapi/util/SystemInfo` you likely use the
compiler embeddable as dependency `org.jetbrains.kotlin:kotlin-compiler-embeddable:1.9.20`.
Use the `org.jetbrains.kotlin:kotlin-compiler:1.9.20` instead or include the 
intellij OpenAPI dependency which contains the missing class.

```kotlin
// https://mvnrepository.com/artifact/com.intellij/openapi
testImplementation("com.intellij:openapi:7.0.3")
```

## IntelliJ Environments

Since the Kotlin Compiler still is coupled with IntelliJ dependencies, you need to make sure to set
some important environment variables.
One of them is `idea.home`. If this variable is not set, you will encounter errors like:
`java.lang.RuntimeException: Could not find installation home path. Please make sure bin/idea.properties is present in the installation directory.`

The kotlin test framework (not the internal compiler test framework) already includes a setup function
in _TestSetupUtils.kt_.

```kotlin
package org.jetbrains.kotlin.test

/**
 * For proper initialization of idea services those two properties should
 *   be set in environment of test. You can setup them manually via build
 *   system of run configurations or just `initIdeaConfiguration` before
 *   running tests using abilities of core test framework you use
 */
fun initIdeaConfiguration() {
    System.setProperty("idea.home", computeHomeDirectory())
    System.setProperty("idea.ignore.disabled.plugins", "true")
}

private fun computeHomeDirectory(): String {
    val userDir = System.getProperty("user.dir")
    return File(userDir ?: ".").canonicalPath
}
```

You need to build your own test runner and call the setup before executes the tests.

A great starting point for plugin development is the [Compiler Plugin Template](https://github.com/demiurg906/kotlin-compiler-plugin-template).