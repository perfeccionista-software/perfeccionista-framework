dependencies {
    compile(project(":pagefactory-api")) {
        because("pagefactory-api module contains platform for current module")
    }
}