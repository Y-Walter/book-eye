plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.ktlint.idea) apply false
	alias(libs.plugins.ktlint.gradle) apply false
	alias(libs.plugins.spring.boot) apply false
	alias(libs.plugins.spring.dependencies.management) apply false
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "kotlin")
	apply(plugin = "org.jlleitschuh.gradle.ktlint")
	apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	group = "walter.y.bookeye"

	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
			vendor.set(JvmVendorSpec.MICROSOFT)
		}
	}

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
	}

	tasks.withType<JavaExec>().configureEach {
		javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
			allWarningsAsErrors = true
		}
	}
}
