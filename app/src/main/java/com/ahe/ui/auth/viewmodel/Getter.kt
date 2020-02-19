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

fun AuthViewModel.getSignUpForNpoFirstScreen(): SignUpForNpoRegister {
    getCurrentViewStateOrNew().let {
        val signUpForNpoRegister = SignUpForNpoRegister(
            0,
            it.signUpAsNpoFirstPageFields?.registration_name.toString(),
            it.signUpAsNpoFirstPageFields?.registration_cName.toString(),
            it.signUpAsNpoFirstPageFields?.registration_email.toString(),
            it.signUpAsNpoFirstPageFields?.registration_website.toString(),
            it.signUpAsNpoFirstPageFields?.registration_ein.toString(),
            "",
            "",
            ""
        )
        return signUpForNpoRegister.let {
            return it
        } ?: getDummyData()
    }
}

fun AuthViewModel.getDummyData(): SignUpForNpoRegister {
    return SignUpForNpoRegister(-1, "", "", "", "", "", "", "", "")
}

fun AuthViewModel.getSignUpForAdvertiserScreen(): SignUpForAdvertiserRegister {
    getCurrentViewStateOrNew().let {
        val signUpForNpoRegister = SignUpForAdvertiserRegister(
            0,
            it.signUpAsAdvertiserFields?.registration_name.toString(),
            it.signUpAsAdvertiserFields?.registration_cName.toString(),
            it.signUpAsAdvertiserFields?.registration_phone.toString(),
            it.signUpAsAdvertiserFields?.registration_website.toString(),
            it.signUpAsAdvertiserFields?.registration_email.toString(),
            "",
            "",
            "",
            ""
        )
        return signUpForNpoRegister.let {
            return it
        } ?: getAdvertiserDummyData()
    }
}

fun AuthViewModel.getAdvertiserDummyData(): SignUpForAdvertiserRegister {
    return SignUpForAdvertiserRegister(-1, "", "", "", "", "", "", "", "", "")
}

