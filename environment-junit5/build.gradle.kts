// Api
val junitVersion = project.properties["junitVersion"] as String?
val junitPlatformVersion = project.properties["junitPlatformVersion"] as String?

dependencies {

    api(project(":environment"))

    api(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "$junitVersion")
    api(group = "org.junit.jupiter", name = "junit-jupiter-params", version = "$junitVersion")
    api(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = "$junitVersion")
    api(group = "org.junit.platform", name = "junit-platform-engine", version = "$junitPlatformVersion")

}
