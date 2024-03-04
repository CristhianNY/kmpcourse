//
//  SwiftAuthProvider.swift
//  IosApp
//
//  Created by cristhian bonilla on 23/02/24.
//

import Foundation
import ComposeApp
import GoogleSignIn
import FirebaseAuth


class SwiftAuthProvider: NSObject, AuthProvider {
    private var loginCallback: LoginCallback?

    static let shared = SwiftAuthProvider()

    func setLoginCallback(callback: LoginCallback?) {
        self.loginCallback = callback
    }

    func signIn() {
        Task {
            do {
                let userData = try await signInGoogle()
                // Invoke the callback on the main thread if necessary
                DispatchQueue.main.async {
                    self.loginCallback?.onLoginSuccess(userData: userData)
                }
            } catch {
                // Convert error to a string message
                let errorMessage = error.localizedDescription
                DispatchQueue.main.async {
                    self.loginCallback?.onLoginFailure(errorMessage: errorMessage)
                }
            }
        }
    }

    func signOut() {
        Task{
            try Auth.auth().signOut()
        }
    }

    func getCurrentUser() -> UserData? {
        
        guard let user = Auth.auth().currentUser else { return nil }
        
        let photo = user.photoURL?.absoluteString ?? ""
        
        let userData = UserData(userId: user.uid,
                                username: user.displayName ?? "",
                                profilePictureUrl: photo)
        return userData
    }
    
    func signInGoogle() async throws -> UserData {
        let helper = SignInGoogleHelper()
        let tokens = try await helper.signIn()
        // This method now returns UserData
        return try await signInWithGoogle(tokens: tokens)
    }


}

// MARK: SIGN IN SSO

// This method should already be correctly implemented as per previous context
extension SwiftAuthProvider {
    func signInWithGoogle(tokens: GoogleSignInResultModel) async throws -> UserData {
        let credential = GoogleAuthProvider.credential(withIDToken: tokens.idToken, accessToken: tokens.accessToken)
        let authDataResult = try await Auth.auth().signIn(with: credential)
        let profilePictureUrlString = authDataResult.user.photoURL?.absoluteString ?? ""
        let userData = UserData(userId: authDataResult.user.uid,
                                username: authDataResult.user.displayName ?? "",
                                profilePictureUrl: profilePictureUrlString)
        return userData
    }
}
