dependencies {

    val junitPlatformVersion : String by rootProject
    val junitVersion : String by rootProject

    api(project(":environment")) {
        because("environment module contains tools for current module")
    }

    api(group = "org.junit.platform", name = "junit-platform-runner", version = junitPlatformVersion)
    api(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    api(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
    api(group = "org.junit.jupiter", name = "junit-jupiter-params", version = junitVersion)

}
