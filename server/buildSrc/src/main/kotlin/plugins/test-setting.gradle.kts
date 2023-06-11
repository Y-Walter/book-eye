package plugins

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    java
    `jvm-test-suite`
    jacoco
}

testing {
    suites {
        getByName<JvmTestSuite>("test") {
            useJUnitJupiter()
            dependencies {
                implementation(project())
                implementation.bundle(libs.findBundle("test").get())
            }
        }

        register<JvmTestSuite>("integrationTest") {
            dependencies {
                useJUnitJupiter()
                implementation(project())
                runtimeOnly("org.springframework.boot:spring-boot-starter-web")
                implementation("org.springframework.boot:spring-boot-starter-test")
                implementation("org.springframework.security:spring-security-test")
                implementation.bundle(libs.findBundle("jackson").get())
                implementation.bundle(libs.findBundle("integrationTest").get())
            }
        }
    }
}