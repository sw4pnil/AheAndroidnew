package com.ahe.ui.dashbord.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindView(`object`: Any, position: Int)
}
