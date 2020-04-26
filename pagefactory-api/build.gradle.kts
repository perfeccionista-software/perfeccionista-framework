dependencies {
    compile(project(":environment")) {
        because("environment module contains platform for current module")
    }
}