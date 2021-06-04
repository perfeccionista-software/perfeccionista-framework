dependencies {

    api(project(":utils")) {
        because("utils module contains tools for current module")
    }

    testImplementation(project(":environment-junit5")) {
        because("utils module contains tools for current tests")
    }

}
