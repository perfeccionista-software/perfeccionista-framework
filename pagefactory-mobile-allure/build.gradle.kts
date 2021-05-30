dependencies {

    val allureVersion : String by rootProject

    api(project(":pagefactory-mobile-api")) {
        because("pagefactory-mobile-api module contains tools for current module")
    }

    api("io.qameta.allure:allure-java-commons:$allureVersion")

}
