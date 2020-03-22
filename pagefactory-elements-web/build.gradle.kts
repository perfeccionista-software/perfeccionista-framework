dependencies {
    compile(project(":pagefactory-api")) {
        because("pagefactory-api module contains api for current module")
    }
    testCompile(project(":pagefactory-asserts")) {
        because("pagefactory-asserts module contains api for current module")
    }
}