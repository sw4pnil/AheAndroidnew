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

fun AuthViewModel.setSignUpForNpoFirstFragment(data: SignUpForNpoRegister) {
    val update = getCurrentViewStateOrNew()
    update.signUpAsNpoFirstPageFields?.registration_name = data.name
    update.signUpAsNpoFirstPageFields?.registration_cName = data.cname
    update.signUpAsNpoFirstPageFields?.registration_email = data.email
    update.signUpAsNpoFirstPageFields?.registration_website = data.website
    update.signUpAsNpoFirstPageFields?.registration_ein = data.ein

    setViewState(update)
}

fun AuthViewModel.setSignUpForAdvertiserFirstFragment(data: SignUpForAdvertiserRegister) {
    val update = getCurrentViewStateOrNew()
    update.signUpAsNpoFirstPageFields?.registration_name = data.name
    update.signUpAsNpoFirstPageFields?.registration_cName = data.cname
    update.signUpAsNpoFirstPageFields?.registration_email = data.email
    update.signUpAsNpoFirstPageFields?.registration_website = data.website
    update.signUpAsNpoFirstPageFields?.registration_phone = data.phone

    setViewState(update)
}


fun AuthViewModel.setSignUpForNpoSecondFragment(data: SignUpForNpoRegister) {
    val update = getCurrentViewStateOrNew()
    update.signUpAsNpoFirstPageFields?.registration_name = data.name
    update.signUpAsNpoFirstPageFields?.registration_cName = data.cname
    update.signUpAsNpoFirstPageFields?.registration_email = data.email
    update.signUpAsNpoFirstPageFields?.registration_website = data.website
    update.signUpAsNpoFirstPageFields?.registration_ein = data.ein
    update.signUpAsNpoFirstPageFields?.registration_phone = data.phone
    update.signUpAsNpoFirstPageFields?.registration_address = data.address
    update.signUpAsNpoFirstPageFields?.registration_about = data.about
    setViewState(update)
}

fun AuthViewModel.setSignUpForAdvertiserFragment(data: SignUpForAdvertiserRegister) {
    val update = getCurrentViewStateOrNew()
    update.signUpAsAdvertiserFields?.registration_name = data.name
    update.signUpAsAdvertiserFields?.registration_cName = data.cname
    update.signUpAsAdvertiserFields?.registration_email = data.email
    update.signUpAsAdvertiserFields?.registration_website = data.website
    update.signUpAsAdvertiserFields?.registration_phone = data.phone
    update.signUpAsAdvertiserFields?.registration_ein = data.ein
    update.signUpAsAdvertiserFields?.registration_category = data.category
    update.signUpAsAdvertiserFields?.registration_image = data.image
    update.signUpAsAdvertiserFields?.registration_about = data.about
    setViewState(update)
}


