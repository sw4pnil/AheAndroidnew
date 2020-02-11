package com.ahe.ui.auth.viewmodel

import com.ahe.constants.USERTYPE

/**
 * Created by swapnil-tml on 09,February,2020
 */

fun AuthViewModel.getUserType(): USERTYPE {
    getCurrentViewStateOrNew().let {
        return it.loginUserTypeField.user_type
    }
}

fun AuthViewModel.getSignUpForMemberFirstScreen(): SignUpForMemberRegister {
    getCurrentViewStateOrNew().let {
        val signUpForMemberRegister = SignUpForMemberRegister(
            0,
            it.signUpAsMemberFirstPageFields?.registration_firstName.toString(),
            it.signUpAsMemberFirstPageFields?.registration_lastName.toString(),
            it.signUpAsMemberFirstPageFields?.registration_email.toString(),
            it.signUpAsMemberFirstPageFields?.registration_country_id.toString(),
            it.signUpAsMemberFirstPageFields?.registration_image.toString(),
            it.signUpAsMemberFirstPageFields?.registration_phone.toString()
        )
        return signUpForMemberRegister.let {
            return it
        } ?: getDummy()
    }
}

fun AuthViewModel.getDummy(): SignUpForMemberRegister {
    return SignUpForMemberRegister(-1, "", "", "", "", "", "")
}