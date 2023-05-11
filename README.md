![moko-maps](https://user-images.githubusercontent.com/5010169/71351401-27c14d80-25a6-11ea-9183-17821f6d4212.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Download](https://img.shields.io/maven-central/v/dev.icerock.moko/maps) ](https://repo1.maven.org/maven2/dev/icerock/moko/maps) ![kotlin-version](https://kotlin-version.aws.icerock.dev/kotlin-version?group=dev.icerock.moko&name=maps)

# Mobile Kotlin maps module
This is a Kotlin Multiplatform library that provides controls of maps to common code.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Samples](#samples)
- [Set Up Locally](#set-up-locally)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Markers** - add markers to map from common code;
- **Route** - draw route by waypoints from common code;
- **Camera** - control camera (zoom, location) from common code.

## Requirements
- Gradle version 6.0+
- Android API 16+
- iOS version 9.0+

## Installation
root build.gradle  
```groovy
allprojects {
    repositories {
        mavenCentral()
        maven { url = "https://mapbox.bintray.com/mapbox" } // if mapbox required
    }
}
```

project build.gradle
```groovy
dependencies {
    commonMainApi("dev.icerock.moko:maps:0.6.0")
    commonMainApi("dev.icerock.moko:maps-mapbox:0.6.0")
}

kotlin.targets
    .matching { it is org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget }
    .configureEach {
        val target = this as org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

        target.binaries
            .matching { it is org.jetbrains.kotlin.gradle.plugin.mpp.Framework }
            .configureEach {
                val framework = this as org.jetbrains.kotlin.gradle.plugin.mpp.Framework
                val frameworks = listOf("Base", "Maps").map { frameworkPath ->
                    project.file("../ios-app/Pods/Mapbox-iOS-SDK/dynamic").path.let { "-F$it" }
                }

                framework.linkerOpts(frameworks)
            }
    }
```

With [mobile-multiplatform-gradle-plugin](https://github.com/icerockdev/mobile-multiplatform-gradle-plugin) cocoapods configuration simplest:
`build.gradle.kts`:
```kotlin
cocoaPods {
    podsProject = file("ios-app/Pods/Pods.xcodeproj")
    
    precompiledPod(
        scheme = "Mapbox",
        onlyLink = true
    ) { podsDir ->
        listOf(File(podsDir, "Mapbox-iOS-SDK/dynamic"))
    }
}
```

project Podfile
```ruby
pod 'Mapbox-iOS-SDK', '5.5.0'

```

## Samples
Please see more examples in the [sample directory](sample).

## Set Up Locally
Before open project need to setup `gradle.properties` with tokens:
```
# mapbox tokens by guide https://docs.mapbox.com/android/maps/guides/install/
mapbox.secretToken=YOUR_SECRET_MAPBOX_KEY
mapbox.publicToken=YOUR_PUBLIC_MAPBOX_KEY

```

# ios info.plist setup with tokens:
```
MGLMapboxAccessToken=YOUR_PUBLIC_MAPBOX_KEY
```
add the following entry to your `.netrc` file:
```
machine api.mapbox.com
login mapbox
password YOUR_SECRET_MAPBOX_KEY
```

- The [maps directory](maps) contains the base classes for all maps providers;
- The [maps-mapbox directory](maps-mapbox) contains the mapbox implementation;
- In [sample directory](sample) contains sample apps for Android and iOS; plus the mpp-library connected to the apps.

*before compilation of iOS target required `pod install` in `sample/ios-app` directory*

## Contributing
All development (both new features and bug fixes) is performed in the `develop` branch. This way `master` always contains the sources of the most recently released version. Please send PRs with bug fixes to the `develop` branch. Documentation fixes in the markdown files are an exception to this rule. They are updated directly in `master`.

The `develop` branch is pushed to `master` on release.

For more details on contributing please see the [contributing guide](CONTRIBUTING.md).

## License
        
    Copyright 2019 IceRock MAG Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
