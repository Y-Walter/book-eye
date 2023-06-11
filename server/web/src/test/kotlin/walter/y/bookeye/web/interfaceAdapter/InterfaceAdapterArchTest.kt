package walter.y.bookeye.web.interfaceAdapter

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures
import org.junit.jupiter.api.Test

class InterfaceAdapterArchTest {
    @Test
    fun checkInterfaceAdapterDependencies() {
        val importedClasses = ClassFileImporter().importPackages("walter.y.bookeye.web.interfaceAdapter")
        val rule = Architectures.layeredArchitecture().consideringOnlyDependenciesInLayers()
            .layer("API").definedBy("walter.y.bookeye.web.interfaceAdapter.api..")
            .layer("Gateway").definedBy("walter.y.bookeye.web.interfaceAdapter.gateway..")
            .layer("Repository").definedBy("walter.y.bookeye.web.interfaceAdapter.repository..")
            .whereLayer("API").mayNotBeAccessedByAnyLayer()
            .whereLayer("Gateway").mayOnlyBeAccessedByLayers("Repository")
            .whereLayer("Repository").mayNotBeAccessedByAnyLayer()

        rule.check(importedClasses)
    }
}
