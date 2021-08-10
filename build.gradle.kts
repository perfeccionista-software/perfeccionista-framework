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
    `maven-publish`
    `java-gradle-plugin`
    id("com.github.kt3k.coveralls") version "2.11.0"
    id("org.sonarqube") version "3.1.1"
    id("io.qameta.allure") version "2.8.1"
    id("com.github.ben-manes.versions") version "0.38.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
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
    "perfeccionista-framework",
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
    project.plugins.withId("java-gradle-plugin") {
        project.configure<GradlePluginDevelopmentExtension> {
            isAutomatedPublishing = false
        }
    }
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

//    apply(plugin = "checkstyle")
    apply(plugin = "java")
    apply(plugin = "java-gradle-plugin")
    apply(plugin = "jacoco")
    apply(plugin = "signing")
    apply(plugin = "io.qameta.allure")
    apply(plugin = "io.spring.dependency-management")

    project.plugins.withId("java-gradle-plugin") { // only do it if it's actually applied
        project.configure<GradlePluginDevelopmentExtension> {
            isAutomatedPublishing = false
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "UTF-8"
    }

    val cucumberVersion = "6.10.2"
    val junitVersion = "5.7.2"
    val fasterxmlVersion = "2.12.3"
    val allureVersion = "2.14.0"

    dependencyManagement {
        imports {
            mavenBom("com.fasterxml.jackson:jackson-bom:$fasterxmlVersion")
            mavenBom("org.junit:junit-bom:$junitVersion")
        }
        dependencies {
            dependency("org.jetbrains:annotations:21.0.1")
            dependency("org.apiguardian:apiguardian-api:1.1.2")

            dependency("com.fasterxml.jackson.core:jackson-core:$fasterxmlVersion")
            dependency("com.fasterxml.jackson.core:jackson-annotations:$fasterxmlVersion")
            dependency("com.fasterxml.jackson.core:jackson-databind:$fasterxmlVersion")

            dependency("org.junit.platform:junit-platform-runner:1.7.2")
            dependency("org.junit.jupiter:junit-jupiter-api:$junitVersion")
            dependency("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
            dependency("org.junit.jupiter:junit-jupiter-params:$junitVersion")

            dependency("io.qameta.allure:allure-java-commons:$allureVersion")
            dependency("io.qameta.allure:allure-junit5:$allureVersion")
            dependency("io.qameta.allure:allure-cucumber6-jvm:$allureVersion")

            dependency("org.mockito:mockito-core:3.9.0")

            dependency("cglib:cglib:3.3.0")

            dependency("org.seleniumhq.selenium:selenium-java:3.141.59")
            dependency("io.github.bonigarcia:webdrivermanager:4.3.1")

            dependency("io.cucumber:cucumber-java:$cucumberVersion")
            dependency("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")

            dependency("io.appium:java-client:7.5.0")

            dependency("androidx.test:core:1.3.0")
            dependency("androidx.test.espresso:espresso-core:3.3.0")
        }
        generatedPomCustomization {
            enabled(false)
        }
    }

    dependencies {
        implementation(group = "org.jetbrains", name = "annotations")
        implementation(group = "org.apiguardian", name = "apiguardian-api")

        implementation(group = "com.fasterxml.jackson.core", name = "jackson-core")
        implementation(group = "com.fasterxml.jackson.core", name = "jackson-annotations")
        implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind")

        testImplementation(group = "org.junit.platform", name = "junit-platform-runner")
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-engine")
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params")

        testImplementation(group = "io.qameta.allure", name = "allure-java-commons")
        testImplementation(group = "io.qameta.allure", name = "allure-junit5")
        testImplementation(group = "org.mockito", name = "mockito-core")
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
                    versionMapping {
                        allVariants {
                            fromResolutionResult()
                        }
                    }
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
