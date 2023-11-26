package io.mths.companionate.runners

import io.mths.companionate.services.AnnotationProvider
import io.mths.companionate.services.ExtensionRegistrarConfigurator
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms
import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.TargetBackend
import org.jetbrains.kotlin.test.backend.BlackBoxCodegenSuppressor
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.builders.fir2IrStep
import org.jetbrains.kotlin.test.directives.CodegenTestDirectives.DUMP_IR
import org.jetbrains.kotlin.test.directives.configureFirParser
import org.jetbrains.kotlin.test.model.DependencyKind
import org.jetbrains.kotlin.test.model.FrontendKinds
import org.jetbrains.kotlin.test.runners.RunnerWithTargetBackendForTestGeneratorMarker

open class AbstractJvmBoxTest : BaseTestRunner(), RunnerWithTargetBackendForTestGeneratorMarker {
    override val targetBackend = TargetBackend.JVM_IR

    override fun TestConfigurationBuilder.configuration() {
        globalDefaults {
            frontend = FrontendKinds.FIR
            targetBackend = TargetBackend.JVM_IR
            targetPlatform = JvmPlatforms.defaultJvmPlatform
            dependencyKind = DependencyKind.Binary
        }

        configureFirParser(FirParser.Psi)

        defaultDirectives {
            + DUMP_IR
        }

        commonFirWithPluginFrontendConfiguration()
        fir2IrStep()

        useConfigurators(::ExtensionRegistrarConfigurator, ::AnnotationProvider)
        useAfterAnalysisCheckers(::BlackBoxCodegenSuppressor)
    }
}
