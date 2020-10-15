val cglibVersion: String by rootProject

dependencies {
    api(project(":environment")) {
        because("environment module contains platform for current module")
    }
    api(group = "cglib", name = "cglib", version = cglibVersion)
}