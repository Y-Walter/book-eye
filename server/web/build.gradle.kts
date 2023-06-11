version = project.file("version.txt")
    .readText()
    .trim()

plugins {
    id("plugins.test-setting")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    runtimeOnly("com.mysql:mysql-connector-j")
}
