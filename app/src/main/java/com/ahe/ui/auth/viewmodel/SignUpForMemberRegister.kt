package com.ahe.ui.auth.viewmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "member_signup_first_page")
class SignUpForMemberRegister (

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "pk")
        var pk: Int,

        @ColumnInfo(name = "fname")
        var fname: String,

        @ColumnInfo(name = "lname")
        var lname: String,

        @ColumnInfo(name = "email")
        var email: String,

        @ColumnInfo(name = "country_id")
        var country_id: String,

        @ColumnInfo(name = "image")
        var image: String,

        @ColumnInfo(name = "phone")
        var phone: String


    ) : Parcelable {

    override fun toString(): String {
        return "SignUpForMemberRegister(fname='$fname', lname='$lname', email='$email', country_id='$country_id', image='$image', phone=$phone)"
    }
}

