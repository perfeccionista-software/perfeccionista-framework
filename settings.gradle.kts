rootProject.name = "perfeccionista-framework"

include("utils")
include("environment-api")
include("environment")
include("pagefactory-api")
include("pagefactory")
include("pagefactory-bdd-api")
include("pagefactory-bdd")
include("pagefactory-bdd-engine")
include("demo-app")

val jVersion = JavaVersion.current()
require (jVersion.isJava8Compatible) {
    "This project needs $jVersion or compatible. Currently executing with Java ${jVersion.majorVersion}."
}