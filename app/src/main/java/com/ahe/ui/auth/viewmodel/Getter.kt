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