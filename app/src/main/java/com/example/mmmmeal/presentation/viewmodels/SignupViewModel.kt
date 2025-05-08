package com.example.mmmmeal.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mmmmeal.presentation.model.AuthUiState
import com.example.mmmmeal.utils.mapFirebaseExceptionToMessage
import com.google.firebase.auth.FirebaseAuth

class SignupViewModel : ViewModel() {

    var uiState by mutableStateOf(AuthUiState())

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun onRepeatPasswordChange(repeatPassword: String) {
        uiState = uiState.copy(repeatPassword = repeatPassword)
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }

    fun registerUser() {
        if (uiState.email.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(
                errorMessage = "Email or password cannot be empty."
            )
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)
        auth.createUserWithEmailAndPassword(uiState.email, uiState.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    uiState = uiState.copy(
                        isLoading = false,
                        successMessage = "Signup successful!"
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
