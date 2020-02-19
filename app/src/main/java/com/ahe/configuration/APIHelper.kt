package com.ahe.configuration

import com.ahe.R
import com.ahe.data.models.*
import com.ahe.data.models.responsenetwork.SignupResponseWrapper
import com.ahe.data.models.responsenetwork.UserInfoResponseWrapper
import com.ahe.util.Utils.debug
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class APIHelper : BaseAPIHelper() {

    private fun getString(stringId: Int): String {
        return App.getInstance().getString(stringId)
    }

    private fun showGeneralizedError(): String {
        return getString(R.string.response_msg_common)
    }

    fun loginManual(userInfo: LoginUserDto,
        onRequestComplete: OnRequestComplete<UserInfoResponseWrapper>
    ) {
        apiService.login(userInfo).enqueue(object : Callback<UserInfoResponseWrapper> {
            override fun onFailure(call: Call<UserInfoResponseWrapper>, t: Throwable) {
                debug("Unable to submit post to API." + t.printStackTrace())
                try {
                    var message: String = showGeneralizedError()
                    if (t.message != null) message = t.message!!
                    onRequestComplete.onFailure(message, 400)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<UserInfoResponseWrapper>, response: Response<UserInfoResponseWrapper>) {
                if (response.isSuccessful) {
                    onRequestComplete.onSuccess(response.body()!!)
                } else {
                    try {
                        val jsonString = JsonParser().parse(response.errorBody()!!.charStream())
                        val message: String = jsonString.asJsonObject["message"].asString
                        val code: Int = jsonString.asJsonObject["code"].asInt

                        onRequestComplete.onFailure(message, code)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    fun signupDonor(donorMemberInfo: DonorMemberDTO,
                    onRequestComplete: OnRequestComplete<SignupResponseWrapper>
    ) {
        apiService.donorSignup(donorMemberInfo).enqueue(object : Callback<SignupResponseWrapper> {
            override fun onFailure(call: Call<SignupResponseWrapper>, t: Throwable) {
                debug("Unable to submit post to API." + t.printStackTrace())
                try {
                    var message: String = showGeneralizedError()
                    if (t.message != null) message = t.message!!
                    onRequestComplete.onFailure(message, 400)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<SignupResponseWrapper>, response: Response<SignupResponseWrapper>) {
                if (response.isSuccessful) {
                    onRequestComplete.onSuccess(response.body()!!)
                } else {
                    debug("something went wrong")
                    /*try {
                        val jsonString = JsonParser().parse(response.errorBody()!!.charStream())
                        val message: String = jsonString.asJsonObject["message"].asString
                        val code: Int = jsonString.asJsonObject["code"].asInt

                        onRequestComplete.onFailure(message, code)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }*/
                }
            }
        })
    }


    fun signupAdvertiser(advertiserInfo: AdvertiserDTO,
                    onRequestComplete: OnRequestComplete<SignupResponseWrapper>
    ) {
        apiService.signupAdvertiser(advertiserInfo).enqueue(object : Callback<SignupResponseWrapper> {
            override fun onFailure(call: Call<SignupResponseWrapper>, t: Throwable) {
                debug("Unable to submit post to API." + t.printStackTrace())
                try {
                    var message: String = showGeneralizedError()
                    if (t.message != null) message = t.message!!
                    onRequestComplete.onFailure(message, 400)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<SignupResponseWrapper>, response: Response<SignupResponseWrapper>) {
                if (response.isSuccessful) {
                    onRequestComplete.onSuccess(response.body()!!)
                } else {
                    try {
                        val jsonString = JsonParser().parse(response.errorBody()!!.charStream())
                        val message: String = jsonString.asJsonObject["message"].asString
                        val code: Int = jsonString.asJsonObject["code"].asInt

                        onRequestComplete.onFailure(message, code)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }


    fun signupFundRaiser(fundRaiserInfo: FundRaiserDTO,
                         onRequestComplete: OnRequestComplete<SignupResponseWrapper>
    ) {
        apiService.signupFundriser(fundRaiserInfo).enqueue(object : Callback<SignupResponseWrapper> {
            override fun onFailure(call: Call<SignupResponseWrapper>, t: Throwable) {
                debug("Unable to submit post to API." + t.printStackTrace())
                try {
                    var message: String = showGeneralizedError()
                    if (t.message != null) message = t.message!!
                    onRequestComplete.onFailure(message, 400)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<SignupResponseWrapper>, response: Response<SignupResponseWrapper>) {
                if (response.isSuccessful) {
                    onRequestComplete.onSuccess(response.body()!!)
                } else {
                    try {
                        val jsonString = JsonParser().parse(response.errorBody()!!.charStream())
                        val message: String = jsonString.asJsonObject["message"].asString
                        val code: Int = jsonString.asJsonObject["code"].asInt

                        onRequestComplete.onFailure(message, code)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    fun adsCategory(onRequestComplete: OnRequestComplete<List<AdvetisementCategoryDTO>>
    ) {
        apiService.AdvertiserCategory().enqueue(object : Callback<List<AdvetisementCategoryDTO>> {
            override fun onFailure(call: Call<List<AdvetisementCategoryDTO>>, t: Throwable) {
                debug("Unable to submit post to API." + t.printStackTrace())
                try {
                    var message: String = showGeneralizedError()
                    if (t.message != null) message = t.message!!
                    onRequestComplete.onFailure(message, 400)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<List<AdvetisementCategoryDTO>>, response: Response<List<AdvetisementCategoryDTO>>) {
                if (response.isSuccessful) {
                    onRequestComplete.onSuccess(response.body()!!)
                } else {
                    try {
                        val jsonString = JsonParser().parse(response.errorBody()!!.charStream())
                        val message: String = jsonString.asJsonObject["message"].asString
                        val code: Int = jsonString.asJsonObject["code"].asInt

                        onRequestComplete.onFailure(message, code)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }


    fun changePassword(oldPassword:String,newPassword:String,userType:String,userId:Int,
                    onRequestComplete: OnRequestComplete<SignupResponseWrapper>
    ) {
        apiService.changePassword(oldPassword,newPassword,userType,userId).enqueue(object : Callback<SignupResponseWrapper> {
            override fun onFailure(call: Call<SignupResponseWrapper>, t: Throwable) {
                debug("Unable to submit post to API." + t.printStackTrace())
                try {
                    var message: String = showGeneralizedError()
                    if (t.message != null) message = t.message!!
                    onRequestComplete.onFailure(message, 400)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<SignupResponseWrapper>, response: Response<SignupResponseWrapper>) {
                if (response.isSuccessful) {
                    onRequestComplete.onSuccess(response.body()!!)
                } else {
                    try {
                        val jsonString = JsonParser().parse(response.errorBody()!!.charStream())
                        val message: String = jsonString.asJsonObject["message"].asString
                        val code: Int = jsonString.asJsonObject["code"].asInt

                        onRequestComplete.onFailure(message, code)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    companion object {
        private const val RESULT_OK = 200
        private const val RESULT_CREATED = 201
        private const val HTTP_UNAUTHORIZED = 401
        private lateinit var instance: APIHelper

        fun refreshBase() {
            resetField(this, "instance")
        }

        @Synchronized
        fun init(app: App): APIHelper {
            return if (::instance.isInitialized) {
                instance
            } else {
                instance = APIHelper()
                instance.setApplication(app)
                instance
            }
        }
    }
}

fun resetField(target: Any, fieldName: String) {
    val field = target.javaClass.getDeclaredField(fieldName)
    with(field) {
        isAccessible = true
        set(target, null)
    }
}
