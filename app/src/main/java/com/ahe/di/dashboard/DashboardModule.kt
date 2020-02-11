package com.ahe.di.dashboard

import android.content.SharedPreferences
import com.ahe.api.dashboard.OpenApiDashboardService
import com.ahe.persistence.AccountPropertiesDao
import com.ahe.persistence.AuthTokenDao
import com.ahe.repository.dashboard.DashboardRepository
import com.ahe.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DashboardModule {

    @DashboardScope
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApiDashboardService {
        return retrofitBuilder
            .build()
            .create(OpenApiDashboardService::class.java)
    }

    @DashboardScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        openApiDashboardService: OpenApiDashboardService,
        preferences: SharedPreferences,
        editor: SharedPreferences.Editor
    ): DashboardRepository {
        return DashboardRepository(
            authTokenDao,
            accountPropertiesDao,
            openApiDashboardService,
            sessionManager,
            preferences,
            editor
        )
    }

}