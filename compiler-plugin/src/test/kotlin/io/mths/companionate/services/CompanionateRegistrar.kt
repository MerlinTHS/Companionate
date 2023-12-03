package io.mths.companionate.services

import io.mths.companionate.CompanionateDirectives.COMPANIONATE_ANNOTATIONS
import io.mths.companionate.FirCompanionateRegistrar
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices

class CompanionateRegistrar(testServices: TestServices) : EnvironmentConfigurator(testServices) {

	@ExperimentalCompilerApi
	override fun ExtensionStorage.registerCompilerExtensions(module: TestModule, configuration: CompilerConfiguration) {
        val annotations = module.directives[COMPANIONATE_ANNOTATIONS]
		FirExtensionRegistrarAdapter.registerExtension(FirCompanionateRegistrar(annotations))
    }
}
