import SwiftUI
import ComposeApp
import FirebaseCore

@main
struct iOSApp: App {
    
    init(){
        FirebaseApp.configure()
        MainViewControllerKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
