package com.ahe.api.auth.network_responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Data : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cname")
    @Expose
    var companyName: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("website")
    @Expose
    var website: String? = null

    @SerializedName("reg_no")
    @Expose
    var reg_no: String? = null

    @SerializedName("cat_id")
    @Expose
    var cat_id: String? = null


    @SerializedName("about")
    @Expose
    var about: Boolean = true

    @SerializedName("balance")
    @Expose
    var balance: String? = null

    @SerializedName("subscription_ends")
    @Expose
    var subscription_ends: Any? = null

    @SerializedName("image")
    @Expose
    var image: Any? = null

    @SerializedName("role")
    @Expose
    var role: Any? = null

}