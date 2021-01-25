import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

plugins {
    id("com.bmuschko.docker-remote-api") version "6.7.0"
}

tasks.create("buildImage", DockerBuildImage::class) {
    group = "docker"

    inputs.files(file("index.html"), file("js"))

    inputDir.set(file("."))
    images.add("perfeccionista/demo-app:latest")
}

tasks.findByName("build")?.dependsOn("buildImage")
