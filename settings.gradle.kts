rootProject.name = "perfeccionista-framework"

include("utils")
include("demo-app")
include("demo-app-assets")
include("bdd-engine")
include("environment")
include("pagefactory-mobile-api")
include("pagefactory-mobile-appium")
include("pagefactory-mobile-cucumber-api")
include("pagefactory-web-api")
include("pagefactory-web-cucumber-api")
include("pagefactory-web-selenium")

val jVersion = JavaVersion.current()
require (jVersion.isJava8Compatible) {
    "This project needs $jVersion or compatible. Currently executing with Java ${jVersion.majorVersion}."
}
