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
    var registartionNumber: String? = null

    @SerializedName("cat_id")
    @Expose
    var webstoreCategoryId: String? = null


    @SerializedName("about")
    @Expose
    var about: Boolean = true

    @SerializedName("balance")
    @Expose
    var mobile: String? = null

    @SerializedName("image")
    @Expose
    var picUrl: Any? = null


    @SerializedName("subscription_ends")
    @Expose
    var city: Any? = null

    @SerializedName("image")
    @Expose
    var image: Any? = null

    /*   @SerializedName("verifyToken")
       @Expose
       var state: Any? = null

       @SerializedName("last_login_at")
       @Expose
       var pincode: Any? = null

       @SerializedName("last_login_ip")
       @Expose
       var googleId: Any? = null

       @SerializedName("created_at")
       @Expose
       var googleToken: Any? = null

       @SerializedName("updated_at")
       @Expose
       var bucketName: String? = null

       @SerializedName("status")
       @Expose
       var address: Any? = null*/


}