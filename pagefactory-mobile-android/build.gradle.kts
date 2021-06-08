dependencies {

    api(project(":pagefactory-mobile-api")) {
        because("pagefactory-mobile-api module contains tools for current module")
    }
    api(project(":utils-android")) {
        because("utils-android module contains tools for current module")
    }

}
