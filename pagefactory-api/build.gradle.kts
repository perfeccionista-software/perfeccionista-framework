dependencies {
    api(project(":environment")) {
        because("environment module contains platform for current module")
    }
}
