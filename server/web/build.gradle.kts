version = project.file("version.txt")
    .readText()
    .trim()

plugins {
    id("plugins.test-setting")
}

dependencies {
    project(":book-eye-test-infra")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    runtimeOnly("com.mysql:mysql-connector-j")
}

tasks.getByName("test") {
    val testInfra = project(":book-eye-test-infra")
    dependsOn(testInfra.tasks.getByName("dockerComposeUp"))
    finalizedBy(testInfra.tasks.getByName("dockerComposeDown"))
}
