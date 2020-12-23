dependencies {

    val allureVersion : String by rootProject
    val cucumberVersion : String by rootProject

    api(project(":pagefactory-web-api")) {
        because("pagefactory-web-api module contains api for current module")
    }
    api(project(":environment-cucumber-api")) {
        because("environment-cucumber-api module contains api for current module")
    }

    testImplementation(project(":demo-app-assets")) {
        because("demo-app-assets module contains api for current module")
    }
    testImplementation(group = "io.cucumber", name = "cucumber-java", version = cucumberVersion)
    testImplementation(group = "io.cucumber", name = "cucumber-junit-platform-engine", version = cucumberVersion)
    testImplementation(group = "io.qameta.allure", name = "allure-cucumber6-jvm", version = allureVersion)

//    testImplementation(group = "io.cucumber", name= "cucumber-junit", version= cucumberVersion)
//    testImplementation(group = "io.cucumber", name= "cucumber-junit-platform-engine", version= cucumberVersion)

}