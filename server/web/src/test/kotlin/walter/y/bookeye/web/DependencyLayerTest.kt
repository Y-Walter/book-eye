package walter.y.bookeye.web

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures
import org.junit.jupiter.api.Test

class DependencyLayerTest {
    @Test
    fun checkDependencies() {
        val importedClasses = ClassFileImporter().importPackages("walter.y.bookeye.web")
        val rule = Architectures.layeredArchitecture().consideringOnlyDependenciesInLayers()
            .layer("InterfaceAdapter").definedBy("walter.y.bookeye.web.interfaceAdapter..")
            .layer("UseCase").definedBy("walter.y.bookeye.web.useCase..")
            .layer("Domain").definedBy("walter.y.bookeye.web.domain..")
            .whereLayer("Domain").mayNotAccessAnyLayer()
            .whereLayer("UseCase").mayOnlyAccessLayers("Domain")
            .whereLayer("InterfaceAdapter").mayOnlyAccessLayers("Domain", "UseCase")

        rule.check(importedClasses)
    }
}
