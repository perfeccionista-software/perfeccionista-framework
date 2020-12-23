dependencies {

    val cucumberVersion : String by rootProject

    api(project(":environment")) {
        because("environment module contains tools for current module")
    }

    api(group = "io.cucumber", name = "cucumber-java", version = cucumberVersion)

    testImplementation(group = "io.cucumber", name = "cucumber-java", version = cucumberVersion)
    testImplementation(group = "io.cucumber", name = "cucumber-junit-platform-engine", version = cucumberVersion)

}