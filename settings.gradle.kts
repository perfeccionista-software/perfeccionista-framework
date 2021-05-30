rootProject.name = "perfeccionista-framework"

include("demo-app")
include("demo-app-assets")
include("demo-app-mobile-assets")
include("utils")
include("bdd-engine")
include("environment")
include("environment-cucumber-api")
include("pagefactory-api")
include("pagefactory-mobile-api")
include("pagefactory-mobile-allure")
include("pagefactory-mobile-appium")
include("pagefactory-mobile-appium-espresso")
include("pagefactory-mobile-appium-xcuitest")
include("pagefactory-mobile-cucumber")
include("pagefactory-web-api")
include("pagefactory-web-allure")
include("pagefactory-web-selenium")
include("pagefactory-web-cucumber")
include("pagefactory-mobile-espresso")

val jVersion = JavaVersion.current()
require (jVersion.isJava8Compatible) {
    "This project needs $jVersion or compatible. Currently executing with Java ${jVersion.majorVersion}."
}
