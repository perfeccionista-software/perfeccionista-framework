dependencies {
    api(project(":pagefactory-mobile-appium")) {
        because("pagefactory-mobile-appium module contains api for current module")
    }

    testImplementation(project(":demo-app-mobile-assets")) {
        because("demo-app-mobile-assets module contains api for current module")
    }
    testImplementation(project(":environment-junit5")) {
        because("utils module contains tools for current tests")
    }

}
