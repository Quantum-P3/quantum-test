package org.quantum.quantumtest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.quantum.quantumtest");

        noClasses()
            .that()
            .resideInAnyPackage("org.quantum.quantumtest.service..")
            .or()
            .resideInAnyPackage("org.quantum.quantumtest.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.quantum.quantumtest.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
