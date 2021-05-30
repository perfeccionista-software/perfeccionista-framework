dependencies {

    val allureVersion : String by rootProject

    api(project(":pagefactory-web-api")) {
        because("pagefactory-web-api module contains tools for current module")
    }

    api("io.qameta.allure:allure-java-commons:$allureVersion")

}
