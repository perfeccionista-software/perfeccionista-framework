dependencies {
    compile(project(":pagefactory-api")) {
        because("pagefactory-api module contains api for current module")
    }
}