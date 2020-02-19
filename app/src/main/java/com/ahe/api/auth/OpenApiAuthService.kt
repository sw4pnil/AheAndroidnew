package com.ahe.api.auth

import androidx.lifecycle.LiveData
import com.ahe.api.auth.network_responses.LoginResponse
import com.ahe.api.auth.network_responses.RegistrationResponse
import com.ahe.repository.auth.PaymetData
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
    fun signupFundraiserRegister(
        @Field("name") name: String,
        @Field("cname") cname: String,
        @Field("email") email: String,
        @Field("website") website: String,
        @Field("reg_no") reg_no: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
        @Field("advertisement_category_id") advertisement_category_id: String,
        @Field("image") image: String,
        @Field("about") about: String,
        @Field("password") password: String,
        @Field("subscription_type") subscription_type: String,
        @Field("amount") amount: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>


    @POST("signupAdvertiser")
    @FormUrlEncoded
    fun signupAdvertiserRegister(
        @Field("name") name: String,
        @Field("cname") cname: String,
        @Field("email") email: String,
        @Field("website") website: String,
        @Field("reg_no") reg_no: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
        @Field("advertisement_category_id") advertisement_category_id: String,
        @Field("image") image: String,
        @Field("about") about: String,
        @Field("password") password: String,
        @Field("subscription_type") subscription_type: String,
        @Field("amount") amount: String,
        @Field("payment_gatway") payment_gatway: String,
        @Field("payment_data") payment_data: PaymetData
    ): LiveData<GenericApiResponse<RegistrationResponse>>

}
