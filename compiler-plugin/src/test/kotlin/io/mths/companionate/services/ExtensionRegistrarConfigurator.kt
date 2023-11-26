package io.mths.companionate.services

import io.mths.companionate.FirCompanionateRegistrar
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices

@OptIn(ExperimentalCompilerApi::class)
class ExtensionRegistrarConfigurator(testServices: TestServices) : EnvironmentConfigurator(testServices) {

    override fun ExtensionStorage.registerCompilerExtensions(module: TestModule, configuration: CompilerConfiguration) {
        FirExtensionRegistrarAdapter.registerExtension(FirCompanionateRegistrar())
    }
}