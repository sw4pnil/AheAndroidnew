package com.ahe.di.dashboard

import com.ahe.ui.dashbord.DashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeDashboardFragment(): DashboardFragment

}