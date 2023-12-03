package io.mths.companionate.runners

import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives.ENABLE_PLUGIN_PHASES
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives.FIR_DUMP
import org.jetbrains.kotlin.test.initIdeaConfiguration
import org.jetbrains.kotlin.test.runners.AbstractFirPsiDiagnosticsTestWithJvmIrBackend
import org.jetbrains.kotlin.test.runners.AbstractKotlinCompilerTest
import org.jetbrains.kotlin.test.runners.baseFirDiagnosticTestConfiguration
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.junit.jupiter.api.BeforeAll

abstract class BaseDiagnosticsTest : AbstractFirPsiDiagnosticsTestWithJvmIrBackend() {
	companion object {
		@BeforeAll
		@JvmStatic
		fun setUp() {
			initIdeaConfiguration()
		}
	}
}

abstract class BaseBoxTest : AbstractKotlinCompilerTest() {
    companion object {
        @BeforeAll
        @JvmStatic
        fun setUp() {
            initIdeaConfiguration()
        }
    }

    override fun createKotlinStandardLibrariesPathProvider() =
        EnvironmentBasedStandardLibrariesPathProvider
}

fun TestConfigurationBuilder.commonFirWithPluginFrontendConfiguration() {
    baseFirDiagnosticTestConfiguration()

    defaultDirectives {
        + ENABLE_PLUGIN_PHASES
        + FIR_DUMP
    }
}
