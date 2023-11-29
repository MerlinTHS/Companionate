package io.mths.companionate

import com.google.auto.service.AutoService
import io.mths.companionate.cli.CompanionateConfigKeys.ANNOTATION
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter

@OptIn(ExperimentalCompilerApi::class)
@AutoService(CompilerPluginRegistrar::class)
class CompanionateRegistrar : CompilerPluginRegistrar() {
    override val supportsK2 = true

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
		val annotations = configuration.get(ANNOTATION) ?: return
		FirExtensionRegistrarAdapter.registerExtension(FirCompanionateRegistrar(annotations))
    }
}
