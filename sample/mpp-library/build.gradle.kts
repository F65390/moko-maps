plugins {
    id("com.android.library")
    id("android-base-convention")
    id("detekt-convention")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform.android-manifest")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("dev.icerock.mobile.multiplatform-resources")
    id("dev.icerock.mobile.multiplatform.cocoapods")
}

kotlin{
    android()
    ios()
}

dependencies {
    commonMainImplementation(libs.coroutines)
    commonMainImplementation(libs.mokoResources)
    commonMainApi(libs.mokoGeo)
    commonMainApi(libs.mokoMvvm)
    commonMainApi(libs.mokoPermissions)
    commonMainApi(projects.maps)
    commonMainApi(projects.mapsMapbox)
    "androidMainImplementation"(libs.lifecycle)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.icerockdev.library"
}

framework {
    export(libs.mokoPermissions)
    export(projects.maps)
    export(projects.mapsMapbox)
}

cocoaPods {
    precompiledPod(
        scheme = "Mapbox",
        onlyLink = true
    ) { podsDir ->
        listOf(File(podsDir, "Mapbox-iOS-SDK/dynamic"))
    }
}
