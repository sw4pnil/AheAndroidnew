package com.ahe.models

import android.os.Parcel
import android.os.Parcelable

class CampaignsDetails(): Parcelable {

   // var userImage: String? = null
    var campaignTitle: String? = null
    var campaignShortDesc: String? = null
    var campaignNumber: String? = null
    var campaignStatus: String? = null
    var campaignName:String? = null
    var receivedAmount: String? = null
    var dateCampaign: String? = null


    constructor(parcel: Parcel) : this() {
        campaignTitle = parcel.readString()
        campaignShortDesc = parcel.readString()
        campaignNumber = parcel.readString()
        campaignStatus = parcel.readString()
        campaignName = parcel.readString()
        receivedAmount = parcel.readString()
        dateCampaign = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(campaignTitle)
        parcel.writeString(campaignShortDesc)
        parcel.writeString(campaignNumber)
        parcel.writeString(campaignStatus)
        parcel.writeString(campaignName)
        parcel.writeString(receivedAmount)
        parcel.writeString(dateCampaign)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CampaignsDetails> {
        override fun createFromParcel(parcel: Parcel): CampaignsDetails {
            return CampaignsDetails(parcel)
        }

        override fun newArray(size: Int): Array<CampaignsDetails?> {
            return arrayOfNulls(size)
        }
    }


}