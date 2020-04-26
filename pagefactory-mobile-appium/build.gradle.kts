val appiumVersion: String by rootProject

dependencies {
    compile(project(":pagefactory-mobile-api")) {
        because("pagefactory-mobile-api module contains api for current module")
    }

    compile(group = "io.appium", name = "java-client", version = appiumVersion) {
        exclude(module="spring-context")
    }
}