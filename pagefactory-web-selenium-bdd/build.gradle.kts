dependencies {
    compile(project(":pagefactory-web-selenium")) {
        because("pagefactory-web-selenium module contains api for current module")
    }

    val cucumberVersion : String by rootProject
//    testImplementation(group = "io.cucumber", name= "cucumber-junit", version= cucumberVersion)
//    testImplementation(group = "io.cucumber", name= "cucumber-junit-platform-engine", version= cucumberVersion)
    implementation(group = "io.cucumber", name= "cucumber-java", version= cucumberVersion)
}