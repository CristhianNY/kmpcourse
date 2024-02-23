//
//  SwiftAuthProvider.swift
//  IosApp
//
//  Created by cristhian bonilla on 23/02/24.
//

import Foundation
import ComposeApp

class SwiftAuthProvider: NSObject, AuthProvider {
    static let shared = AuthProviderBridge()
    func signIn() {
        // Llamada al SDK de Firebase o Google para iniciar sesión
    }

    func signOut() {
        // Llamada al SDK de Firebase o Google para cerrar sesión
    }

    func getCurrentUser() -> UserData? {
        // Obtener el usuario actual
        return nil // Transforma el usuario de Firebase/Google en UserData
    }
}
