package com.ahe.configuration

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import com.ahe.data.models.UserDTO
import com.ahe.util.JsonUtils

class AppPreferences//        .super()
    (var context: Context) {
    lateinit var instance: AppPreferences

    companion object {
        private var USER_JSON = "USER_JSON"
        private var USER_TOKEN = "USER_TOKEN"
        private var USER_TYPE = "USER_TYPE"
    }

    protected var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun init(context: Context): AppPreferences {
        return if (::instance.isInitialized) {
            instance
        } else {
            instance = AppPreferences(context)
            instance
        }
    }

    fun setUserProfile(value: UserDTO) {
        setString(USER_JSON, JsonUtils.convertPojoToString(value))
    }

    fun getUserProfile(): UserDTO {
        val value = sharedPreferences.getString(USER_JSON, null)
        return JsonUtils.convertStringToPojo(value, UserDTO::class.java) as UserDTO
    }

    fun setUserToken(token: String) {
        setString(USER_TOKEN, token)
    }

    fun getUserToken(): String? {
        return getString(USER_TOKEN)
    }

    fun setUserTYPE(token: String) {
        setString(USER_TYPE, token)
    }

    fun getUserTYPE(): String? {
        return getString(USER_TYPE)
    }

    private fun setString(key: String, value: String) {
        val editor: Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun setFloat(key: String, value: Float) {
        val editor: Editor = sharedPreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    private fun setInt(key: String, value: Int) {
        val editor: Editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    private fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0F)
    }

    private fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    private fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun setBoolean(key: String?, value: Boolean) {
        val editor: Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun clearAll() {
        val editor: Editor = sharedPreferences.edit()
        editor.apply()
        editor.clear()
        editor.apply()
    }
}