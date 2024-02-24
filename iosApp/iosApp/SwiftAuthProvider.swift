//
//  SwiftAuthProvider.swift
//  IosApp
//
//  Created by cristhian bonilla on 23/02/24.
//

import Foundation
import ComposeApp


class SwiftAuthProvider: NSObject, AuthProvider {
    private var loginCallback: LoginCallback?

    static let shared = SwiftAuthProvider()

    func setLoginCallback(callback: LoginCallback?) {
        self.loginCallback = callback
    }

    func signIn() {
        let loginSuccessful = true

        if loginSuccessful {
            // Simulación de datos de usuario obtenidos tras un login exitoso
            let userData = UserData(userId: "123", username: "UserExample", profilePictureUrl: "http://example.com/image.png")
            // Aquí invocas el callback de Kotlin Multiplatform
            self.loginCallback?.onLoginSuccess(userData: userData)
        } else {
            let errorMessage = "Error en el login"
            self.loginCallback?.onLoginFailure(errorMessage: errorMessage)
        }
    }

    func signOut() {
        // Llamada al SDK de Firebase o Google para cerrar sesión
    }

    func getCurrentUser() -> UserData? {
        // Obtener el usuario actual
        return nil // Transforma el usuario de Firebase/Google en UserData
    }
}
