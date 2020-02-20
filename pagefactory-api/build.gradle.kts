val seleniumVersion: String by rootProject
val wdmVersion: String by rootProject
val appiumVersion: String by rootProject

dependencies {
    compile(project(":environment")) {
        because("environment module contains platform for current module")
    }

    compile(group = "org.seleniumhq.selenium", name = "selenium-java", version = seleniumVersion)
    compile(group = "io.github.bonigarcia", name = "webdrivermanager", version = wdmVersion)
    compile(group = "io.appium", name = "java-client", version = appiumVersion)

}