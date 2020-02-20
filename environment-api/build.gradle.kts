dependencies {
    compile(project(":utils")) {
        because("utils module contains tools for current module")
    }
}