package com.ahe.repository

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsHelper(context:Context) {
    private val mSharedPrefsHelper: SharedPreferences

    init {
        mSharedPrefsHelper = context.getSharedPreferences(AHE_PREFERENCE, Context.MODE_PRIVATE)
    }

    var loginFlag : Boolean
       get() = mSharedPrefsHelper.getBoolean(LOGIN_FLAG, false)
       set(value) = saveBool(LOGIN_FLAG, value)

    private fun saveBool(key:String, value: Boolean){
        mSharedPrefsHelper.edit().putBoolean(key, value).apply()
    }

    companion object {
        private val AHE_PREFERENCE = "AHE_PREFERENCE"
        private val LOGIN_FLAG = "login_flag"
    }
}