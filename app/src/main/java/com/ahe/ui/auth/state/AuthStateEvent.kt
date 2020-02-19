package com.ahe.ui.auth.state

import android.provider.ContactsContract

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

    data class SignUpAsNpoAttemptEvent(
        val name: String,
        val cname: String,
        val email: String,
        val website: String,
        val ein: String,
        val phone: String,
        val address: String,
        val about: String,
        val image: String,
        val password: String,
        val confirm_password: String

    ): AuthStateEvent()

    data class SignUpAsAdvertiserAttemptEvent(
        val name: String,
        val cname: String,
        val email: String,
        val website: String,
        val ein: String,
        val phone: String,
        val about: String,
        val image: String,
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