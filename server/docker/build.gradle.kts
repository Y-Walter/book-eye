plugins {
    alias(libs.plugins.docker.compose)
}

dockerCompose {
    setDockerComposeFile(project.file("book-eye-infra/docker-compose.yml"))
}
