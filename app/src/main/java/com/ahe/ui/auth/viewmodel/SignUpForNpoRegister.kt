package com.ahe.ui.auth.viewmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created by swapnil-tml on 13,February,2020
 */
@Parcelize
@Entity(tableName = "npo_signup_first_page")
class SignUpForNpoRegister(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    var pk: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "cname")
    var cname: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "website")
    var website: String,

    @ColumnInfo(name = "ein")
    var ein: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "about")
    var about: String

) : Parcelable {
    override fun toString(): String {
        return "SignUpForNpoRegister(pk=$pk, name='$name', cname='$cname', email='$email', website='$website', ein='$ein', phone='$phone', address='$address', about='$about')"
    }
}