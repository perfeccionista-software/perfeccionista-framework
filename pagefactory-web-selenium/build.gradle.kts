val seleniumVersion: String by rootProject
val wdmVersion: String by rootProject

dependencies {
    api(project(":pagefactory-web-api")) {
        because("pagefactory-web-api module contains api for current module")
    }
    api(group = "org.seleniumhq.selenium", name = "selenium-java", version = seleniumVersion)
    api(group = "io.github.bonigarcia", name = "webdrivermanager", version = wdmVersion)

    testImplementation(project(":demo-app-assets")) {
        because("demo-app-assets module contains api for current module")
    }

}