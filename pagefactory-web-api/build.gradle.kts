val cglibVersion: String by rootProject

dependencies {
    compile(project(":pagefactory-api")) {
        because("pagefactory-api module contains api for current module")
    }

    compile(group = "cglib", name = "cglib", version = cglibVersion)
}