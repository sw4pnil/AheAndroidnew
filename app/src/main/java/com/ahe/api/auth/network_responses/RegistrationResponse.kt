package com.ahe.api.auth.network_responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegistrationResponse(

    @SerializedName("success_message")
    @Expose
    var response: String,

    @SerializedName("error_message")
    @Expose
    var errorMessage: String,

    @SerializedName("auth_token")
    @Expose
    var token: String,

    @SerializedName("data")
    @Expose
    var data: Data,

    @SerializedName("status")
    @Expose
    var status: Boolean
) {
    override fun toString(): String {
        return "RegistrationResponse(response='$response', errorMessage='$errorMessage', token='$token', data=$data, status=$status)"
    }
}