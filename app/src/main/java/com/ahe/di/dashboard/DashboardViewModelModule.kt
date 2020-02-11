package com.ahe.di.dashboard

import androidx.lifecycle.ViewModel
import com.ahe.di.ViewModelKey
import com.ahe.ui.dashbord.DashBoardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    abstract fun bindAuthViewModel(dashboardViewModel: DashBoardViewModel): ViewModel

}