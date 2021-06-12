val cglibVersion: String by rootProject

dependencies {

    api(project(":pagefactory-api")) {
        because("pagefactory-api module contains platform for current module")
    }
    api(group = "cglib", name = "cglib", version = cglibVersion)

    testImplementation(project(":environment-junit5")) {
        because("utils module contains tools for current tests")
    }
}
