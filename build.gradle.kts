import io.qameta.allure.gradle.task.AllureReport
import org.gradle.internal.classpath.Instrumented.systemProperty

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        google()
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    java
    jacoco
    signing
    checkstyle
    `java-gradle-plugin`
    `maven-publish`
    id("com.github.kt3k.coveralls") version "2.11.0"
    id("org.sonarqube") version "3.1.1"
    id("io.qameta.allure") version "2.8.1"
    id("com.github.ben-manes.versions") version "0.38.0" apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

// Global registry workaround https://github.com/ben-manes/gradle-versions-plugin
// until https://github.com/ben-manes/gradle-versions-plugin/pull/504 approved
if (!project.plugins.hasPlugin("com.github.ben-manes.versions")) {
    apply(plugin = "com.github.ben-manes.versions")
}

val ossrhUsername = systemProperty("ossrhUsername", null)
val ossrhPassword = systemProperty("ossrhPassword", null)

nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set("2a04cf3a01d7b7")
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(ossrhUsername)
            password.set(ossrhPassword)
        }
    }
}

val notToPublish = listOf(
    "demo-app",
    "bdd-engine",
    "demo-app-assets",
    "demo-app-mobile-assets",
    "environment-cucumber-api",
    "pagefactory-mobile-allure",
    "pagefactory-mobile-android",
    "pagefactory-mobile-api",
    "pagefactory-mobile-appium",
    "pagefactory-mobile-appium-espresso",
    "pagefactory-mobile-appium-xcuitest",
    "pagefactory-mobile-cucumber",
    "pagefactory-mobile-espresso",
    "pagefactory-web-cucumber"
)

configure(listOf(rootProject)) {
    description = "Perfeccionista framework"
    group = "io.perfeccionista.framework"
    version = rootProject.property("frameworkVersion") ?: "local"
}

configure(subprojects.filter { it.name != "demo-app" }) {

    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }

    val project = this
    group = "io.perfeccionista.framework"
    version = rootProject.property("frameworkVersion") ?: "local"

    extra["isRelease"] = !version.toString().endsWith("-SNAPSHOT")

    val jetBrainsAnnotationsVersion: String by rootProject
    val apiGuardianVersion: String by rootProject
    val junitPlatformVersion: String by rootProject
    val junitVersion: String by rootProject
    val jacksonVersion: String by rootProject
    val allureVersion: String by rootProject

//    apply(plugin = "checkstyle")
    apply(plugin = "java")
    apply(plugin = "java-gradle-plugin")
    apply(plugin = "jacoco")
    apply(plugin = "signing")
    apply(plugin = "io.qameta.allure")

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "UTF-8"
    }

    dependencies {
        implementation(group = "org.jetbrains", name = "annotations", version = jetBrainsAnnotationsVersion)
        implementation(group = "org.apiguardian", name = "apiguardian-api", version = apiGuardianVersion)

        implementation(group = "com.fasterxml.jackson.core", name = "jackson-core", version = jacksonVersion)
        implementation(group = "com.fasterxml.jackson.core", name = "jackson-annotations", version = jacksonVersion)
        implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = jacksonVersion)

        testImplementation(group = "org.junit.platform", name = "junit-platform-runner", version = junitPlatformVersion)
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params", version = junitVersion)

        testImplementation(group = "io.qameta.allure", name = "allure-java-commons", version = allureVersion)
        testImplementation(group = "io.qameta.allure", name = "allure-junit5", version = allureVersion)
        testImplementation(group = "org.mockito", name = "mockito-core", version = "3.9.0")
    }

    allure {
        autoconfigure = true
        version = allureVersion
    }

    tasks.register("cleanAllure", org.gradle.api.tasks.Delete::class) {
        group = "build"

        delete("allure-results")
        delete("$buildDir/allure-results")
        delete("$buildDir/reports/allure-report")
    }

    tasks.register("allureReportLocal", AllureReport::class) {
        resultsDirs.add(file("allure-results"))
    }

    tasks.register("clearAllureLocal", org.gradle.api.tasks.Delete::class) {
        group = "build"
        delete("allure-results")
    }

    tasks.clean {
        finalizedBy("cleanAllure")
    }

//    checkstyle {
//        toolVersion = "8.7"
//        configFile = rootProject.file("checkstyle/checkstyle.xml")
//        configProperties["suppressionsFile"] = rootProject.file("checkstyle/suppressions.xml")
//    }

    jacoco {
        toolVersion = "0.8.4"
    }

    tasks.jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }

    tasks.test {
        finalizedBy("jacocoTestReport")
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
            info
        }
    }

    tasks.jar {
        manifest {
            attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            ))
        }
    }

    if (project.name !in notToPublish) {

        apply(plugin = "org.gradle.maven-publish")

        val javadocJar = task<Jar>("javadocJar") {
            from("javadoc")
            archiveClassifier.set("javadoc")
        }

        val sourcesJar = task<Jar>("sourcesJar") {
            from(sourceSets["main"].allSource)
            archiveClassifier.set("sources")
        }

        publishing {
            publications {
                create<MavenPublication>("mavenCentral") {
                    from(components["java"])
                    suppressAllPomMetadataWarnings()
                    pom {
                        name.set(project.name)
                        description.set("Module ${project.name} of Perfeccionista Framework.")
                        url.set("https://github.com/perfeccionista-software/perfeccionista-framework")
                        licenses {
                            license {
                                name.set("The Apache License, Version 2.0")
                                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }
                        developers {
                            developer {
                                id.set("iRSV")
                                name.set("Sergey Razuvaev")
                                email.set("svrazuvaev@yandex.ru")
                            }
                            developer {
                                id.set("sshuvalov")
                                name.set("Stanislav Shuvalov")
                                email.set("shuvalov.stanislav@gmail.com")
                            }
                        }
                        scm {
                            developerConnection.set("scm:git:git://github.com/perfeccionista-software/perfeccionista-framework")
                            connection.set("scm:git:git://github.com/perfeccionista-software/perfeccionista-framework")
                            url.set("https://github.com/perfeccionista-software/perfeccionista-framework")
                        }
                        issueManagement {
                            system.set("GitHub Issues")
                            url.set("https://github.com/perfeccionista-software/perfeccionista-framework/issues")
                        }
                    }
                    artifacts {
                        artifact(javadocJar)
                        artifact(sourcesJar)
                    }
                }
            }
        }
        signing {
            sign(publishing.publications["mavenCentral"])
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
        excludeTags = setOf("local-test")
    }
}

tasks.register<JacocoMerge>("jacocoMergeTest") {
    destinationFile = file(allTestsCoverageFile)
    executionData = project.fileTree("${rootDir}") {
        include("**/build/jacoco/test.exec")
    }
}

tasks.register("jacocoRootMerge") {
    dependsOn("jacocoMergeTest")
}

tasks.register<JacocoReport>("jacocoRootReport") {
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
