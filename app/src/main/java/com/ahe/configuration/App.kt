package com.ahe.configuration

import android.annotation.SuppressLint
import android.app.Application

class App : Application() {
    companion object {
        private lateinit var instance: App
        private lateinit var apiHelper: APIHelper
        @SuppressLint("StaticFieldLeak")
        private lateinit var appPreferences: AppPreferences

        @Synchronized
        fun getInstance(): App {
            if (!(::instance.isInitialized)) instance = App()
            return instance
        }
        @Synchronized
        fun getPreferences(): AppPreferences {
            return appPreferences
        }

        @Synchronized
        fun getAPIHelper(): APIHelper {
            return apiHelper
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiHelper = APIHelper.init(this)
//        FirebaseApp.initializeApp(this)
        val appPre = AppPreferences(this)
        appPreferences = appPre.init(this)
//        AppCompatDelegate.setDefaultNightMode(appPreferences.getTheme())
    }
}