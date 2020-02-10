package com.ahe.ui.auth.viewmodel

import com.ahe.constants.USERTYPE

/**
 * Created by swapnil-tml on 09,February,2020
 */

fun AuthViewModel.setUserType(user_type: USERTYPE) {
    val update = getCurrentViewStateOrNew()
    update.loginUserTypeField?.user_type = user_type
    setViewState(update)
}

