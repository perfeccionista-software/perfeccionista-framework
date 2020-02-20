import io.qameta.allure.gradle.task.AllureReport

repositories {
    google()
    maven("https://plugins.gradle.org/m2/")
}

plugins {
    java
    checkstyle
    `java-gradle-plugin`
    jacoco
    `maven-publish`
    id("com.github.kt3k.coveralls") version "2.9.0"
    id("org.sonarqube") version "2.8"
    id("io.qameta.allure") version "2.8.1"
}

rootProject.apply {
    description = "Perfeccionista framework"
}

configure(allprojects.filter { !it.name.contains("demo-app") }) {
    group = "io.perfeccionista.framework"
}

configure(subprojects.filter { it.name != "demo-app" }) {
    version = rootProject.property("frameworkVersion") ?: "local"

    extra["isRelease"] = !version.toString().endsWith("-SNAPSHOT")

    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }

    val junitVersion: String by rootProject
    val junitPlatformVersion: String by rootProject
    val allureVersion: String by rootProject

    val notToPublish = kotlin.collections.listOf("demo-app")

    apply(plugin = "checkstyle")
    apply(plugin = "java")
    apply(plugin = "java-gradle-plugin")
    apply(plugin = "jacoco")
    apply(plugin = "maven-publish")
    apply(plugin = "io.qameta.allure")

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "UTF-8"
    }

    dependencies {
        compile(group = "org.jetbrains", name = "annotations", version = "17.0.0")

        compile(group = "org.junit.platform", name = "junit-platform-runner", version = junitPlatformVersion)
        compile(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
        compile(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)

        testCompile(group = "org.mockito", name = "mockito-core", version = "3.0.0")
    }

    checkstyle {
        toolVersion = "8.7"
        configFile = rootProject.file("checkstyle/checkstyle.xml")
        configProperties["suppressionsFile"] = rootProject.file("checkstyle/suppressions.xml")
    }

    jacoco {
        toolVersion = "0.8.4"
    }

    tasks.test {
        finalizedBy("jacocoTestReport")
        useJUnitPlatform()
    }

    tasks.register("cleanAllure", org.gradle.api.tasks.Delete::class) {
        group = "build"

        delete("allure-results")
        delete("$buildDir/allure-results")
        delete("$buildDir/reports/allure-report")
    }

    tasks.clean {
        finalizedBy("cleanAllure")
    }

    tasks.jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }

    allure {
        autoconfigure = true
        version = allureVersion
    }

    tasks.register("allureReportLocal", AllureReport::class) {
        resultsDirs.add(file("allure-results"))
    }

    tasks.register("clearAllureLocal", org.gradle.api.tasks.Delete::class) {
        group = "build"
        delete("allure-results")
    }

    if (project.name !in notToPublish) {
        apply(plugin = "org.gradle.maven-publish")
        val sourcesJar = task<org.gradle.api.tasks.bundling.Jar>("sourcesJar") {
            from(sourceSets["main"].allSource)
            archiveClassifier.set("sources")
        }

        publishing {
            publications {
                create<org.gradle.api.publish.maven.MavenPublication>(project.name) {
                    from(components["java"])
                    artifacts {
                        artifact(sourcesJar)
                    }
                }
            }
        }
    }
}

val allTestsCoverageFile = "$rootDir/build/jacoco/rootTestsCoverage.exec"
var allTestsCoverageReport = "${buildDir}/reports/jacoco/test/jacocoTestReport.xml"
val jacocoSubprojects = project.subprojects.filter { it.name != "demo-app" }

tasks.test {
    jacocoSubprojects.forEach { dependsOn("${it.name}:jacocoTestReport") }
    finalizedBy("jacocoRootReport")
    useJUnitPlatform {
        excludeTags = kotlin.collections.setOf("local-test")
    }
}

tasks.register<org.gradle.testing.jacoco.tasks.JacocoMerge>("jacocoMergeTest") {
    destinationFile = file(allTestsCoverageFile)
    executionData = project.fileTree("${rootDir}") {
        include("**/build/jacoco/test.exec")
    }
}

tasks.register("jacocoRootMerge") {
    dependsOn("jacocoMergeTest")
}

tasks.register<org.gradle.testing.jacoco.tasks.JacocoReport>("jacocoRootReport") {
    group = "verification"
    dependsOn("jacocoRootMerge")
    reports {
        xml.isEnabled = true
        xml.destination = file(allTestsCoverageReport)
        html.isEnabled = true
    }
    additionalSourceDirs.setFrom(files(jacocoSubprojects.map { it.sourceSets["main"].allSource.srcDirs }.flatten()))
    sourceDirectories.setFrom(files(jacocoSubprojects.map { it.sourceSets["main"].allSource.srcDirs }.flatten()))
    classDirectories.setFrom(files(jacocoSubprojects.map { it.sourceSets["main"].output }.flatten()))
    executionData.setFrom(files(allTestsCoverageFile))
}

apply(plugin = "com.github.kt3k.coveralls")

coveralls {
    sourceDirs = jacocoSubprojects.map { it.sourceSets["main"].allSource.srcDirs }.flatten().map { it.absolutePath }
}

tasks.coveralls {
    dependsOn("jacocoRootReport")
}

tasks.sonarqube {
    dependsOn("jacocoRootReport")
}

sonarqube {
    properties {
        property("sonar.projectName", "perfeccionista-framework")
        property("sonar.projectKey", "perfeccionista")
        property("sonar.coverage.jacoco.xmlReportPaths", allTestsCoverageReport)
    }
}

//tasks.register<org.gradle.api.DefaultTask>("publishLocal") {
//    group = "prfc_publish"
//    dependsOn(tasks.findByPath(                  ":bdd:publishBddPublicationToMavenLocal"))
//    dependsOn(tasks.findByPath(                 ":core:publishCorePublicationToMavenLocal"))
//    dependsOn(tasks.findByPath(":junit-cucumber-engine:publishJunit-cucumber-enginePublicationToMavenLocal"))
//    dependsOn(tasks.findByPath(          ":pagefactory:publishPagefactoryPublicationToMavenLocal"))
//}
//
//tasks.register<org.gradle.api.DefaultTask>("publishRemote") {
//    group = "prfc_publish"
//    dependsOn(tasks.findByPath(                  ":bdd:publishBddPublicationToMavenRepository"))
//    dependsOn(tasks.findByPath(                 ":core:publishCorePublicationToMavenRepository"))
//    dependsOn(tasks.findByPath(":junit-cucumber-engine:publishJunit-cucumber-enginePublicationToMavenRepository"))
//    dependsOn(tasks.findByPath(          ":pagefactory:publishPagefactoryPublicationToMavenRepository"))
//}
