package com.ahe.ui.dashbord.state

import android.os.Parcelable
import com.ahe.constants.USERTYPE
import com.ahe.models.AuthToken
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DashBoardViewState(
    var authToken: AuthToken? = null
) : Parcelable






