package com.example.mmmmeal.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mmmmeal.presentation.model.AuthUiState
import com.example.mmmmeal.utils.mapFirebaseExceptionToMessage
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(AuthUiState())

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }

    fun loginUser() {
        if (uiState.email.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(
                errorMessage = "Email or password cannot be empty."
            )
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)
        auth.signInWithEmailAndPassword(uiState.email, uiState.password)
            .addOnCompleteListener { task ->
                uiState = if (task.isSuccessful) {
                    uiState.copy(
                        isLoading = false,
                        successMessage = "Login successful!"
                    )
                } else {
                    val userErrorMessage = mapFirebaseExceptionToMessage(task.exception)
                    uiState.copy(
                        isLoading = false,
                        errorMessage = userErrorMessage ?: "Unknown error"
                    )
                }
            }
    }


    fun clearErrorMessage() {
        uiState = uiState.copy(errorMessage = null)
    }
}
