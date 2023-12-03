package io.mths.companionate

import io.mths.companionate.runners.AbstractDiagnosticTest
import io.mths.companionate.runners.AbstractJvmBoxTest
import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5

fun main() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(testDataRoot = "testData", testsRoot = "test-gen") {
            testClass<AbstractJvmBoxTest> { model("box") }
			testClass<AbstractDiagnosticTest> { model("diagnostics") }
        }
    }
}
