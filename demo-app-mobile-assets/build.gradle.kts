dependencies {
    api(project(":pagefactory-mobile-appium-espresso")) {
        because("pagefactory-mobile-appium-espresso module contains api for current module")
    }
    api(project(":pagefactory-mobile-appium-xcuitest")) {
        because("pagefactory-mobile-appium-xcuitest module contains api for current module")
    }

}
