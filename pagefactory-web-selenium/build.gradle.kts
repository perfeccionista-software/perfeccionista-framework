val seleniumVersion: String by rootProject
val wdmVersion: String by rootProject

dependencies {
    compile(project(":pagefactory-web-api")) {
        because("pagefactory-web-api module contains api for current module")
    }

    compile(group = "org.seleniumhq.selenium", name = "selenium-java", version = seleniumVersion)
    compile(group = "io.github.bonigarcia", name = "webdrivermanager", version = wdmVersion)
}