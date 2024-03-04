import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let signInViewModel = KotlinBridge().getSignInViewModel()
        SwiftAuthProvider.shared.setLoginCallback(callback: signInViewModel.createLoginCallback())
        return MainViewControllerKt.MainViewController(authProvider: SwiftAuthProvider.shared)
      }


    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



