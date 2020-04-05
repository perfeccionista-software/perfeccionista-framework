dependencies {
    compile(project(":pagefactory-elements-web")) {
        because("pagefactory-elements-web module contains api for current module")
    }
}