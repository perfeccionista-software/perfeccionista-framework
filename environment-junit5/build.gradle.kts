dependencies {

    api(project(":environment"))

    api(group = "org.junit.platform", name = "junit-platform-engine")
    api(group = "org.junit.jupiter", name = "junit-jupiter-api")
    api(group = "org.junit.jupiter", name = "junit-jupiter-params")

    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")

}
