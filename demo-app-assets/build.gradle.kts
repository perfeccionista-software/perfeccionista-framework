dependencies {
    api(project(":pagefactory-web-selenium")) {
        because("pagefactory-web-selenium module contains api for current module")
    }
    api(project(":pagefactory-web-cucumber")) {
        because("pagefactory-web-cucumber module contains api for current module")
    }
}
