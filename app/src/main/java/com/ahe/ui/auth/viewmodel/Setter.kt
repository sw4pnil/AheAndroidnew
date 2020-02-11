package com.ahe.ui.auth.viewmodel

import com.ahe.constants.USERTYPE

/**
 * Created by swapnil-tml on 09,February,2020
 */

fun AuthViewModel.setUserType(user_type: USERTYPE) {
    val update = getCurrentViewStateOrNew()
    update.loginUserTypeField.user_type = user_type
    setViewState(update)
}

fun AuthViewModel.setSignUpForMemberFirstFragment(data: SignUpForMemberRegister) {
    val update = getCurrentViewStateOrNew()
    update.signUpAsMemberFirstPageFields?.registration_firstName = data.fname
    update.signUpAsMemberFirstPageFields?.registration_lastName = data.lname
    update.signUpAsMemberFirstPageFields?.registration_email = data.email
    update.signUpAsMemberFirstPageFields?.registration_country_id = data.country_id
    update.signUpAsMemberFirstPageFields?.registration_image = data.image
    update.signUpAsMemberFirstPageFields?.registration_phone = data.phone

    setViewState(update)
}



