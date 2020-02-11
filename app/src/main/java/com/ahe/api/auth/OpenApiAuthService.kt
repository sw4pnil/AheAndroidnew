package com.ahe.api.auth

import androidx.lifecycle.LiveData
import com.ahe.api.auth.network_responses.LoginResponse
import com.ahe.api.auth.network_responses.RegistrationResponse
import com.ahe.util.GenericApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OpenApiAuthService {

    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("role") role: String
    ): LiveData<GenericApiResponse<LoginResponse>>

    @POST("account/register")
    @FormUrlEncoded
    fun register(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password2") password2: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>


    @POST("signupDoner")
    @FormUrlEncoded
    fun signupMemberRegister(
        @Field("fname") fname: String,
        @Field("lname") lname: String,
        @Field("email") email: String,
        @Field("country_id") country_id: String,
        @Field("image") image: String,
        @Field("phone") phone: String,
        @Field("password") password: String

    ): LiveData<GenericApiResponse<RegistrationResponse>>


    @POST("signupFundriser")
    @FormUrlEncoded
    fun signupFundriserRegister(
        @Field("name") name: String,
        @Field("cname") cname: String,
        @Field("phone") phone: String,
        @Field("website") website: String,
        @Field("email") email: String,
        @Field("reg_no") reg_no: String,
        @Field("advertisement_category_id") advertisement_category_id: Int,
        @Field("image") image: String,
        @Field("about") about: String,
        @Field("password") password: String,
        @Field("subscription_type") subscription_type: Int,
        @Field("amount") amount: Int,
        @Field("payment_gatway") payment_gatway: Int,
        @Field("payment_data") PaymentData: Int
    ): LiveData<GenericApiResponse<RegistrationResponse>>


    @POST("signupAdvertiser")
    @FormUrlEncoded
    fun signupAdvertiserRegister(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password2") password2: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>

}
