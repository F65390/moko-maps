/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import MultiPlatformLibrary
import UIKit
import GoogleMaps

@UIApplicationMain
class AppDelegate: NSObject, UIApplicationDelegate {
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        GMSServices.provideAPIKey(Bundle.main.object(forInfoDictionaryKey: "GoogleAPIkey") as! String)
        
        // For Mapbox add key "MGLMapboxAccessToken" in Info.plist
        return true
    }
}
