package io.mths.companionate.runners

import io.mths.companionate.CompanionateDirectives
import io.mths.companionate.services.CompanionateRegistrar
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider

abstract class AbstractDiagnosticTest : BaseDiagnosticsTest() {
	override fun TestConfigurationBuilder.configuration() {
		commonFirWithPluginFrontendConfiguration()

		useDirectives(CompanionateDirectives)
		useConfigurators(::CompanionateRegistrar)
	}

	override fun createKotlinStandardLibrariesPathProvider() =
		EnvironmentBasedStandardLibrariesPathProvider
}
