dependencies {
    compile(project(":pagefactory")) {
        because("pagefactory module contains platform for current module")
    }
}