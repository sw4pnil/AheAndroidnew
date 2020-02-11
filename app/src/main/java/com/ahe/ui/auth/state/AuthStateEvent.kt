package com.ahe.ui.auth.state

sealed class AuthStateEvent{

    data class LoginAttemptEvent(
        val email: String,
        val password: String,
        val role: String

    ): AuthStateEvent()

    data class SignUpAsMemberFirestPageAttemptEvent(
        val fname: String,
        val lname: String,
        val email: String,
        val country_id: String,
        val image: String,
        val phone: String,
        val password: String,
        val confirm_password: String

    ): AuthStateEvent()

    data class RegisterAttemptEvent(
        val email: String,
        val username: String,
        val password: String,
        val confirm_password: String
    ): AuthStateEvent()

    class CheckPreviousAuthEvent(): AuthStateEvent()

    class None: AuthStateEvent()
}