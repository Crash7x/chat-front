pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include(":app")
rootProject.name = "WeatherApp"
include(":moduleinjector")
include(":features:weather")
include(":features:cities")
include(":core")
include(":features:selectionofcitydialogwindow")
include(":constants")
