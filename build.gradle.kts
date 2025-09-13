import org.jreleaser.model.Signing

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
        mavenLocal()
        google()
    }
}

plugins {
    java
    signing
    `java-library`
    `maven-publish`
    id("io.qameta.allure") version "2.11.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jreleaser") version "1.19.0"

//    jacoco
//    checkstyle
//    `java-gradle-plugin`
//    id("com.github.kt3k.coveralls") version "2.12.2"
//    id("org.sonarqube") version "4.4.1.3373"
//    id("com.github.ben-manes.versions") version "0.50.0" apply false
}

// Global registry workaround https://github.com/ben-manes/gradle-versions-plugin
// until https://github.com/ben-manes/gradle-versions-plugin/pull/504 approved
//if (!project.plugins.hasPlugin("com.github.ben-manes.versions")) {
//    apply(plugin = "com.github.ben-manes.versions")
//}

val notToPublish = listOf(
    "perfeccionista-framework",
    "demo-app",
    "bdd-engine",
    "demo-app-assets",
    "demo-app-mobile-assets",
    "allure2-invocations",
    "environment-cucumber-api",
    "pagefactory-api",
    "pagefactory-mobile-allure",
    "pagefactory-mobile-android",
    "pagefactory-mobile-api",
    "pagefactory-mobile-appium",
    "pagefactory-mobile-appium-espresso",
    "pagefactory-mobile-appium-xcuitest",
    "pagefactory-mobile-cucumber",
    "pagefactory-mobile-espresso",
    "pagefactory-web-api",
    "pagefactory-web-cucumber",
    "pagefactory-web-elements",
    "pagefactory-web-selenium",
    "utils-android"
)

configure(listOf(rootProject)) {
    description = "Perfeccionista framework"
    group = "io.perfeccionista.framework"
    version = rootProject.property("frameworkVersion") ?: "local"
}

configure(subprojects.filter { it.name != "demo-app" }) {

    repositories {
        mavenCentral()
        mavenLocal()
        google()
    }

    val project = this
    group = "io.perfeccionista.framework"
    version = rootProject.property("frameworkVersion") ?: "local"

    extra["isRelease"] = !version.toString().endsWith("-SNAPSHOT")

//    apply(plugin = "checkstyle")
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "signing")
    apply(plugin = "io.qameta.allure")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "maven-publish")

//    apply(plugin = "java-gradle-plugin")
//    apply(plugin = "jacoco")

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        options.encoding = "UTF-8"
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    // Api
    val jetbrainsAnnotationsVersion = project.properties["jetbrainsAnnotationsVersion"] as String?
    // Implementation
    val slf4jVersion = project.properties["slf4jVersion"] as String?
    val opentest4jVersion = project.properties["opentest4jVersion"] as String?
    val apiguardianVersion = project.properties["apiguardianVersion"] as String?
    val fasterxmlVersion = project.properties["fasterxmlVersion"] as String?
    // Test Implementation
    val junitVersion = project.properties["junitVersion"] as String?
//    val junitPlatformVersion = project.properties["junitPlatformVersion"] as String?
    val allureVersion = project.properties["allureVersion"] as String?
    val mockitoVersion = project.properties["mockitoVersion"] as String?

    dependencies {
        api(group = "org.jetbrains", name = "annotations", version = "$jetbrainsAnnotationsVersion")

        implementation(group = "org.slf4j", name = "slf4j-api", version = "$slf4jVersion")
        implementation(group = "org.opentest4j", name = "opentest4j", version = "$opentest4jVersion")
        implementation(group = "org.apiguardian", name = "apiguardian-api", version = "$apiguardianVersion")

        implementation(group = "com.fasterxml.jackson.core", name = "jackson-core", version = "$fasterxmlVersion")
        implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "$fasterxmlVersion")

        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = "$junitVersion")
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params", version = "$junitVersion")
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "$junitVersion")

        testImplementation(group = "io.qameta.allure", name = "allure-java-commons", version = "$allureVersion")
        testImplementation(group = "io.qameta.allure", name = "allure-junit5", version = "$allureVersion")

        testImplementation(group = "org.mockito", name = "mockito-core", version = "$mockitoVersion")

        testRuntimeOnly(group = "org.slf4j", name = "slf4j-simple", version = "$slf4jVersion")

//        testRuntimeOnly(group = "org.apache.logging.log4j", name = "log4j-api", version = "2.20.0")
//        testRuntimeOnly(group = "org.apache.logging.log4j", name = "log4j-core", version = "2.20.0")
//        testRuntimeOnly(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = "2.20.0")

//        testRuntimeOnly(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
    }

    allure {
        version = allureVersion
    }

//    tasks.register("cleanAllure", org.gradle.api.tasks.Delete::class) {
//        group = "build"
//
//        delete("allure-results")
//        delete("$buildDir/allure-results")
//        delete("$buildDir/reports/allure-report")
//    }

//    tasks.register("allureReportLocal", AllureReport::class) {
//        resultsDirs.add(file("allure-results"))
//    }

//    tasks.register("clearAllureLocal", org.gradle.api.tasks.Delete::class) {
//        group = "build"
//        delete("allure-results")
//    }
//
//    tasks.clean {
//        finalizedBy("cleanAllure")
//    }

//    checkstyle {
//        toolVersion = "8.7"
//        configFile = rootProject.file("checkstyle/checkstyle.xml")
//        configProperties["suppressionsFile"] = rootProject.file("checkstyle/suppressions.xml")
//    }

//    jacoco {
//        toolVersion = "0.8.4"
//    }
//
//    tasks.jacocoTestReport {
//        reports {
//            xml.isEnabled = true
//            html.isEnabled = true
//        }
//    }

    tasks.test {
//        finalizedBy("jacocoTestReport")
        useJUnitPlatform {
            excludeTags = setOf("local-test")
        }
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
        apply(plugin = "org.jreleaser")


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
            repositories {
                maven {
                    url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
                }
            }
        }

        signing {
            sign(publishing.publications["mavenCentral"])
        }

        jreleaser {
            signing {
                active.set(org.jreleaser.model.Active.ALWAYS)
                armored.set(true)
                mode.set(Signing.Mode.FILE)
                publicKey.set("/Users/irsv/.gpg/public.pgp")
                secretKey.set("/Users/irsv/.gpg/private.pgp")
            }
            deploy {
                maven {
                    mavenCentral {
                        create("sonatype") {
                            active.set(org.jreleaser.model.Active.ALWAYS)
                            url.set("https://central.sonatype.com/api/v1/publisher")
                            stagingRepository("build/staging-deploy")
                        }
                    }
                }
            }
        }
    }

}

//val allTestsCoverageFile = "$rootDir/build/jacoco/rootTestsCoverage.exec"
//var allTestsCoverageReport = "${buildDir}/reports/jacoco/test/jacocoTestReport.xml"
//val jacocoSubprojects = project.subprojects.filter { it.name != "demo-app" }


//tasks.register<JacocoMerge>("jacocoMergeTest") {
//    destinationFile = file(allTestsCoverageFile)
//    executionData = project.fileTree("${rootDir}") {
//        include("**/build/jacoco/test.exec")
//    }
//}
//
//tasks.register("jacocoRootMerge") {
//    dependsOn("jacocoMergeTest")
//}

//tasks.register<JacocoReport>("jacocoRootReport") {
//    group = "verification"
//    dependsOn("jacocoRootMerge")
//    reports {
//        xml.isEnabled = true
//        xml.destination = file(allTestsCoverageReport)
//        html.isEnabled = true
//    }
//    additionalSourceDirs.setFrom(files(jacocoSubprojects.map { it.sourceSets["main"].allSource.srcDirs }.flatten()))
//    sourceDirectories.setFrom(files(jacocoSubprojects.map { it.sourceSets["main"].allSource.srcDirs }.flatten()))
//    classDirectories.setFrom(files(jacocoSubprojects.map { it.sourceSets["main"].output }.flatten()))
//    executionData.setFrom(files(allTestsCoverageFile))
//}

//apply(plugin = "com.github.kt3k.coveralls")
//
//coveralls {
//    sourceDirs = jacocoSubprojects.map { it.sourceSets["main"].allSource.srcDirs }.flatten().map { it.absolutePath }
//}
//
//tasks.coveralls {
//    dependsOn("jacocoRootReport")
//}

//tasks.sonarqube {
//    dependsOn("jacocoRootReport")
//}

//sonarqube {
//    properties {
//        property("sonar.projectName", "perfeccionista-framework")
//        property("sonar.projectKey", "perfeccionista")
//        property("sonar.coverage.jacoco.xmlReportPaths", allTestsCoverageReport)
//    }
//}

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
