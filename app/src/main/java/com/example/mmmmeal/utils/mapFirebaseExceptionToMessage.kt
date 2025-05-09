package com.example.mmmmeal.utils

fun mapFirebaseExceptionToMessage(exception: Exception?): String {
    return when (exception?.message) {
        null -> "An unknown error occurred."


        //login Messages

        //invalid email format
        "The email address is badly formatted." -> "Please enter a valid email address."

        //no user found
        "There is no user record corresponding to this identifier. The user may have been deleted." ->
            "No account found with this email."

        //wrong password
        "The password is invalid or the user does not have a password." ->
            "Incorrect password. Please try again."

        //user account disabled
        "The user account has been disabled by an administrator." ->
            "Your account has been disabled. Please contact support."

        //login failed
        "The supplied auth credential is incorrect, malformed or has expired." ->
            "Login failed. The password or email you entered is incorrect."




        //signup Messages

        // email already in use
        "The email address is already in use by another account." ->
            "This email is already registered. Please log in instead."

        //weak password
        "The given password is invalid. [ Password should be at least 6 characters ]" ->
            "Password is too weak. Please use at least 6 characters."

        //email/password sign-up disabled
        "Email/password sign-in is disabled for this project." ->
            "Email/password sign-up is disabled. Please contact support."

        //too many requests
        "Too many requests. Please try again later." ->
            "Too many attempts. Please try again later."




        //network error
        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." ->
            "Network error. Please check your connection."

        else -> "Login failed: ${exception.message}"
    }
}
