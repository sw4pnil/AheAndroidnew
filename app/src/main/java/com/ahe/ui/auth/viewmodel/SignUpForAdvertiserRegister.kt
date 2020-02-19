package com.ahe.ui.auth.viewmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created by swapnil-tml on 15,February,2020
 */

@Parcelize
@Entity(tableName = "advertiser_signup")
class SignUpForAdvertiserRegister(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    var pk: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "cname")
    var cname: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "website")
    var website: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "ein")
    var ein: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "about")
    var about: String


) : Parcelable {
    override fun toString(): String {
        return "SignUpForAdvertiserRegister(pk=$pk, name='$name', cname='$cname', phone='$phone', website='$website', email='$email', ein='$ein', category='$category', image='$image', about='$about')"
    }
}

