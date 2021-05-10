
dependencies {
    api(project(":pagefactory-mobile-api")) {
        because("pagefactory-mobile-api module contains api for current module")
    }

    implementation(group = "androidx.test.espresso", name = "espresso-core", version = "3.3.0")

}
