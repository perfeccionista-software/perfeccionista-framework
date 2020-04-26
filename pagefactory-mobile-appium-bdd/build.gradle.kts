dependencies {
    compile(project(":pagefactory-mobile-appium")) {
        because("pagefactory-mobile-appium module contains api for current module")
    }

    val cucumberVersion : String by rootProject
//    testImplementation(group = "io.cucumber", name= "cucumber-junit", version= cucumberVersion)
//    testImplementation(group = "io.cucumber", name= "cucumber-junit-platform-engine", version= cucumberVersion)
    implementation(group = "io.cucumber", name= "cucumber-java", version= cucumberVersion)
}