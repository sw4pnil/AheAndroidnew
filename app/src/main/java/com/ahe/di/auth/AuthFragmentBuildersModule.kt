package com.ahe.di.auth

import com.ahe.ui.auth.*
import com.ahe.ui.auth.login.LoginFragment
import com.ahe.ui.auth.member.SignUpAsMemberFirstFragment
import com.ahe.ui.auth.member.SignUpAsMemberFragment
import com.ahe.ui.auth.member.SignUpAsMemberSecondFragment
import com.ahe.ui.auth.npo.SignUpAsNpoFirstPageFragment
import com.ahe.ui.auth.npo.SignUpAsNpoIntroFragment
import com.ahe.ui.auth.npo.SignUpAsNpoSecondPageFragment
import com.ahe.ui.auth.npo.SignUpAsNpoThirdPageFragment
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
    abstract fun contributeSignUpAsNpoIntroFragment(): SignUpAsNpoIntroFragment

    @ContributesAndroidInjector()
    abstract fun contributeSignUpAsNpoFirstFragment(): SignUpAsNpoFirstPageFragment

    @ContributesAndroidInjector()
    abstract fun contributeSignUpAsNpoSecondFragment(): SignUpAsNpoSecondPageFragment

    @ContributesAndroidInjector()
    abstract fun contributeSignUpAsNpoThirdFragment(): SignUpAsNpoThirdPageFragment

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