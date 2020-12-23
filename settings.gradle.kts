rootProject.name = "perfeccionista-framework"

include("demo-app")
include("demo-app-assets")
include("utils")
include("bdd-engine")
include("environment")
include("environment-cucumber-api")
include("pagefactory-mobile-api")
include("pagefactory-mobile-appium")
include("pagefactory-mobile-cucumber")
include("pagefactory-web-api")
include("pagefactory-web-selenium")
include("pagefactory-web-cucumber")

val jVersion = JavaVersion.current()
require (jVersion.isJava8Compatible) {
    "This project needs $jVersion or compatible. Currently executing with Java ${jVersion.majorVersion}."
}
