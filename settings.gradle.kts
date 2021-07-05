/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()

        maven { url = uri("https://mapbox.bintray.com/mapbox") }

        jcenter {
            content {
                includeGroup("org.jetbrains.kotlinx")
            }
        }
    }
}

includeBuild("maps-build-logic")

include(":maps")
include(":maps-google")
include(":maps-mapbox")
include(":sample:android-app")
include(":sample:mpp-library")
