package com.ahe.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahe.configuration.App
import com.ahe.configuration.BaseAPIHelper


/**
 * Created by swapnil-tml on 18,February,2020
 */


class ChangePasswordRepository {
    private val changePasswordObjectMutableLiveData = MutableLiveData<SignupResponseWrapper>()
    //    private val sendFcmObjectMutableLiveData = MutableLiveData<UserResponseWrapper>()
    companion object {
        private lateinit var instance: ChangePasswordRepository

        fun getInstance(): ChangePasswordRepository {
            if (!(::instance.isInitialized)) {
                instance = ChangePasswordRepository()
            }
            return instance
        }
    }
    fun changePassword(oldPassword:String,newPassword:String,userType:String,userId:Int): LiveData<SignupResponseWrapper> {
        App.getAPIHelper()
            .changePassword(oldPassword,newPassword,userType,userId, object : BaseAPIHelper.OnRequestComplete<SignupResponseWrapper> {
                override fun onSuccess(any: SignupResponseWrapper) {
                    changePasswordObjectMutableLiveData.postValue(any)
                }

                override fun onFailure(errorMessage: String, errorCode: Int) {
//                    val error = UserInfoResponseWrapper(null, errorMessage,statusCode)
//                    loginObjectMutableLiveData.postValue(error)
                }
            })
        return changePasswordObjectMutableLiveData
    }


    /* fun fcmToken(deviceId: String, fcmToken: String): LiveData<UserResponseWrapper> {
         App.getAPIHelper().fcmToken(deviceId, fcmToken,
             object : BaseAPIHelper.OnRequestComplete<UserResponseWrapper> {
                 override fun onSuccess(any: UserResponseWrapper) {
                     sendFcmObjectMutableLiveData.postValue(any)
                 }

                 override fun onFailure(errorMessage: String, errorCode: Int) {
                     val error = UserResponseWrapper()
                     error.message = errorMessage
                     error.code = errorCode
                     sendFcmObjectMutableLiveData.postValue(error)
                 }
             })
         return sendFcmObjectMutableLiveData
     }*/
}