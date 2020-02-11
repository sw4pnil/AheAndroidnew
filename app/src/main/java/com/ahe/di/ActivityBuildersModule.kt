package com.ahe.di

import com.ahe.di.auth.AuthFragmentBuildersModule
import com.ahe.di.auth.AuthModule
import com.ahe.di.auth.AuthScope
import com.ahe.di.auth.AuthViewModelModule
import com.ahe.di.dashboard.DashboardFragmentBuildersModule
import com.ahe.di.dashboard.DashboardModule
import com.ahe.di.dashboard.DashboardScope
import com.ahe.di.dashboard.DashboardViewModelModule
import com.ahe.di.main.MainFragmentBuildersModule
import com.ahe.di.main.MainModule
import com.ahe.di.main.MainScope
import com.ahe.di.main.MainViewModelModule
import com.ahe.ui.auth.AuthActivity
import com.ahe.ui.dashbord.DashBoardActivity
import com.ahe.ui.main.MainActivity
import com.ahe.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeSplashActivity(): SplashActivity

    @DashboardScope
    @ContributesAndroidInjector(
        modules = [DashboardModule::class, DashboardFragmentBuildersModule::class, DashboardViewModelModule::class]
    )
    abstract fun contributeDashboardActivity(): DashBoardActivity

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class, MainFragmentBuildersModule::class, MainViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}