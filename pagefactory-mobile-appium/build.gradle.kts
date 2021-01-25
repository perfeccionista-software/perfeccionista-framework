val appiumVersion: String by rootProject

dependencies {
    api(project(":pagefactory-mobile-api")) {
        because("pagefactory-mobile-api module contains api for current module")
    }

    api(group = "io.appium", name = "java-client", version = appiumVersion) {
        exclude(module="spring-context")
    }
}
