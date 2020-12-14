dependencies {
    api(project(":pagefactory-web-selenium")) {
        because("pagefactory-web-selenium module contains api for current module")
    }
    api(project(":pagefactory-web-cucumber-api")) {
        because("pagefactory-web-cucumber-api module contains api for current module")
    }
}