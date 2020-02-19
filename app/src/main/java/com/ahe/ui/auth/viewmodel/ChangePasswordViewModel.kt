package com.ahe.ui.auth.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ahe.repository.ChangePasswordRepository

class ChangePasswordViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private var changePasswordRepository: ChangePasswordRepository = ChangePasswordRepository.getInstance()

    fun changePassword(oldPassword:String,newPassword:String,userType:String,userId:Int): LiveData<SignupResponseWrapper> {
        return changePasswordRepository.changePassword(oldPassword,newPassword,userType,userId)
    }
}