val androidXTestVersion: String by rootProject

dependencies {
    api(project(":utils")) {
        because("utils module contains api for current module")
    }
    api(group = "androidx.test", name = "core", version = androidXTestVersion, ext = "aar")
}
