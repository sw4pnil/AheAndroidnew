package com.ahe.ui.auth.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ahe.R
import com.ahe.util.CirclePagerIndicatorDecoration
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_wallet_report.*

class WalletReport : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() =
            WalletReport().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }

    private lateinit var adapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = listOf(
            RecentComment("Premature optimization is the root of all evil"),
            RecentComment("Any sufficiently advanced technology is indistinguishable from magic."),
            RecentComment("Content 01"),
            RecentComment("Content 02"),
            RecentComment("Content 03"),
            RecentComment("Content 04"),
            RecentComment("Content 05")
        )

        adapter = CommentAdapter()
        adapter.replaceItems(items)
        recyclerViewRecentComment.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRecentComment.addItemDecoration(CirclePagerIndicatorDecoration())
        recyclerViewRecentComment.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewRecentComment)

        /* menuDrawer.setOnClickListener {
             (activity as DashboardActivity).openDrawer()
         }*/

    }

    class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
        private var items = listOf<RecentComment>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recent_comment_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]

            holder.textView.text = item.commentText
        }

        fun replaceItems(items: List<RecentComment>) {
            this.items = items
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = items.size

        inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView),
            LayoutContainer {
            val textView = containerView.findViewById<TextView>(R.id.recentCommentText)
        }
    }

}