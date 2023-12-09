pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    buildscript {
        repositories {
            google()
            mavenCentral()
        }
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io")}

    }

    repositories {
        jcenter()
    }


}

rootProject.name = "loginproject"
include(":app")

fun allprojects(function: () -> Unit) {

}
