rootProject.name = "perfeccionista-framework"

include("utils")
include("demo-app")
include("bdd-engine")
include("environment")
include("pagefactory-api")
include("pagefactory-mobile-api")
include("pagefactory-mobile-appium")
include("pagefactory-mobile-appium-bdd")
include("pagefactory-web-api")
include("pagefactory-web-selenium")
include("pagefactory-web-selenium-bdd")

val jVersion = JavaVersion.current()
require (jVersion.isJava8Compatible) {
    "This project needs $jVersion or compatible. Currently executing with Java ${jVersion.majorVersion}."
}
