package com.ahe.api.auth.network_responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse(

    @SerializedName("success_message")
    @Expose
    var response: String,

    @SerializedName("error_message")
    @Expose
    var errorMessage: String,

    @SerializedName("auth_token")
    @Expose
    var token: String,
    @SerializedName("email")
    @Expose
    var email: String,

    @SerializedName("data")
    @Expose
    var data: Data

) {
    override fun toString(): String {
        return "LoginResponse(response='$response', errorMessage='$errorMessage', token='$token', email='$email', data=$data)"
    }
}
