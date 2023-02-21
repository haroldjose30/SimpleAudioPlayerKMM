import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinApplication.start()
    }
    
	var body: some Scene {
		WindowGroup {
            //TODO: Add viewModel on DI
            MainPage(viewModel: MainPageViewModel())
		}
	}
}
