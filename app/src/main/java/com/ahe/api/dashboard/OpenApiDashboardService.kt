package com.ahe.api.dashboard

import androidx.lifecycle.LiveData
import com.ahe.api.auth.network_responses.LoginResponse
import com.ahe.util.GenericApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OpenApiDashboardService {

    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("role") role: String
    ): LiveData<GenericApiResponse<LoginResponse>>

}
