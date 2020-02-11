package com.ahe.ui.dashbord.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahe.R
import com.ahe.models.CampaignsDetails
import com.ahe.ui.dashbord.viewholder.ViewHolder
import com.ahe.view.viewholders.CampaignsViewHolder

class CampaignsListAdapter(
    private val list: ArrayList<CampaignsDetails>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.campaign_item, parent, false)
        return CampaignsViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(list[position], position)
    }
}