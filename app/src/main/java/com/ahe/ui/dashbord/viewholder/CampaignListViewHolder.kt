package com.ahe.view.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ahe.R
import com.ahe.models.CampaignsDetails
import com.ahe.ui.dashbord.viewholder.ViewHolder

class CampaignListViewHolder(
    private val view: View,
    onClickListener: View.OnClickListener
) : ViewHolder(view) {


    private var header: TextView? = null
    private var shorDesc: TextView? = null
    private var viewBtn: Button? = null

    private var campaignsDetails: CampaignsDetails? = null

    init {
        header = view.findViewById(R.id.campaignPurposeTitle)
        shorDesc = view.findViewById(R.id.campaignDescriptionText)
        viewBtn = view.findViewById(R.id.viewBtn)

        viewBtn?.setOnClickListener(onClickListener)
    }

    override fun onBindView(`object`: Any, position: Int) {
        campaignsDetails = `object` as CampaignsDetails

        if (campaignsDetails != null) {
            header?.text = campaignsDetails?.campaignTitle
            shorDesc?.text = campaignsDetails?.campaignShortDesc
        }
    }
}