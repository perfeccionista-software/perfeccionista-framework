dependencies {
    compile(project(":environment-api")) {
        because("environment-api module contains api for current module")
    }
}