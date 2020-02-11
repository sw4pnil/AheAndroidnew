package com.ahe.view.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.ahe.R
import com.ahe.models.CampaignsDetails
import com.ahe.ui.dashbord.viewholder.ViewHolder


class CampaignsViewHolder(
    view: View,
    onClickListener: View.OnClickListener
) : ViewHolder(view) {


    private var header: TextView? = null
    private var shorDesc: TextView? = null
    private var cardView: CardView? = null

    private var campaignsDetails: CampaignsDetails? = null

    init {
        header = view.findViewById(R.id.campaignPurposeTitle)
        shorDesc = view.findViewById(R.id.campaignDescriptionText)
        cardView = view.findViewById(R.id.cardView)
        cardView?.setOnClickListener(onClickListener)
    }

    override fun onBindView(`object`: Any, position: Int) {
        campaignsDetails = `object` as CampaignsDetails

        if (campaignsDetails != null) {
            header?.text = campaignsDetails?.campaignTitle
            shorDesc?.text = campaignsDetails?.campaignShortDesc
        }
    }
}