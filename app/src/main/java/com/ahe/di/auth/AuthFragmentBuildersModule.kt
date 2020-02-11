package com.ahe.di.auth

import com.ahe.ui.auth.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeLauncherFragment(): LauncherFragment

    @ContributesAndroidInjector()
    abstract fun contributeReadMoreFragment(): ReadMoreFragment

    @ContributesAndroidInjector()
    abstract fun contributeChooseRoleFragment(): ChooseRoleFragment

    @ContributesAndroidInjector()
    abstract fun contributeSignUpAsMemberFragment(): SignUpAsMemberFragment

    @ContributesAndroidInjector()
    abstract fun contributeSignUpAsMemberFirstFragment(): SignUpAsMemberFirstFragment

    @ContributesAndroidInjector()
    abstract fun contributeSignUpAsMemberSecondFragment(): SignUpAsMemberSecondFragment

    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector()
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment


}