package com.ahe.ui.dashbord

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahe.R
import com.ahe.models.CampaignsDetails
import com.ahe.ui.auth.AuthActivity
import com.ahe.ui.dashbord.adapter.CampaignsListAdapter
import com.ahe.ui.dashbord.adapter.FeaturedListAdapter
import com.ahe.util.GridDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseDashboardFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cardView -> navReadMore()
        }
    }

    private var listAdapter: CampaignsListAdapter? = null
    private var campaignlistAdapterMoreDesc: CampaignsListAdapter? = null
    private var featuerdlistAdapterMoreDesc: FeaturedListAdapter? = null

    private var campaignDetailsArrayList = ArrayList<CampaignsDetails>()
    private var campaignDetailsArrayListWithMoreDesc = ArrayList<CampaignsDetails>()
    private var position = 0
    private var positionNpo = 0
    private var positionPc = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getDataFromWebservice()
        populateCountryList()
    }

    private fun navReadMore() {
        val intent = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun populateCountryList() {
        val countries = ArrayList<String>()

        countries.add("India")
        countries.add("United States")
        countries.add("Russia")
        activity?.let {
            val countryAdapter =
                ArrayAdapter(it, android.R.layout.simple_selectable_list_item, countries)

            selectCountryEditText.threshold = 1
            selectCountryEditText.setAdapter(countryAdapter)
        }
    }

    private fun getDataFromWebservice() {
        val campaignsDetails = CampaignsDetails()
        campaignsDetails.campaignTitle = getString(R.string.water_for_all_childrens)
        campaignsDetails.campaignShortDesc =
            getString(R.string.lorem_ipsum_dolor_sit_amet_vitasium_in_magna_actellius)

        campaignDetailsArrayList.add(campaignsDetails)

        val campaignDetailsSecond = CampaignsDetails()
        campaignDetailsSecond.campaignTitle = getString(R.string.water_for_all_childrens)
        campaignDetailsSecond.campaignShortDesc =
            getString(R.string.lorem_ipsum_dolor_sit_amet_vitasium_in_magna_actellius)

        campaignDetailsArrayList.add(campaignDetailsSecond)

        listAdapter?.notifyDataSetChanged()

        val campaignsDetails1 = CampaignsDetails()
        campaignsDetails1.campaignTitle = getString(R.string.water_for_all_childrens)
        campaignsDetails1.campaignShortDesc =
            getString(R.string.lorem_ipsum_dolor_sit_amet_vitasium_in_magna_actellius) + getString(R.string.lorem_ipsum_dolor_sit_amet_vitasium_in_magna_actellius)

        campaignDetailsArrayListWithMoreDesc.add(campaignsDetails1)

        val campaignDetailsSecond1 = CampaignsDetails()
        campaignDetailsSecond1.campaignTitle = getString(R.string.water_for_all_childrens)
        campaignDetailsSecond1.campaignShortDesc =
            getString(R.string.lorem_ipsum_dolor_sit_amet_vitasium_in_magna_actellius) + getString(R.string.lorem_ipsum_dolor_sit_amet_vitasium_in_magna_actellius)

        campaignDetailsArrayListWithMoreDesc.add(campaignDetailsSecond1)

        campaignlistAdapterMoreDesc?.notifyDataSetChanged()

    }

    private fun setAdapter() {

        val gridLayoutManager =
            activity?.let { androidx.recyclerview.widget.GridLayoutManager(it, 2) }
        recyclerViewFeatureCampaign.layoutManager = gridLayoutManager
        activity?.let {
            recyclerViewFeatureCampaign.addItemDecoration(GridDividerItemDecoration(it))
        }
        recyclerViewFeatureCampaign.setHasFixedSize(true)
        val animator = recyclerViewFeatureCampaign.itemAnimator
        if (animator is androidx.recyclerview.widget.SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        recyclerViewFeatureCampaign.itemAnimator?.changeDuration = 0

        listAdapter = CampaignsListAdapter(campaignDetailsArrayList, this)
        featuerdlistAdapterMoreDesc =
            FeaturedListAdapter(campaignDetailsArrayListWithMoreDesc, this)
        recyclerViewFeatureCampaign!!.adapter = featuerdlistAdapterMoreDesc

        Log.e(TAG, "hello")
        recyclerViewNpoCampaign!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            activity,
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerViewNpoCampaign!!.adapter = listAdapter

        recyclerViewPublicCommunityCampaign!!.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )

        recyclerViewPublicCommunityCampaign!!.adapter = listAdapter

        val scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerViewFeatureCampaign.computeHorizontalScrollOffset()
                val cellWidth = recyclerViewFeatureCampaign.getChildAt(0).measuredWidth
                if (offset % cellWidth == 0) {
                    position = offset / cellWidth

                    val count = recyclerViewFeatureCampaign.adapter!!.itemCount
                    Log.e(TAG, " $count")
                    //next button
                    if ((position + 1) < (count - 1)) {
                        featureCampaignFrontArrow.alpha = 1.0f
                    } else {

                        featureCampaignFrontArrow.alpha = 0.5f
                    }

                    //back button
                    if ((position - 1) > -1) {
                        featureCampaignBackArrow.alpha = 1.0f
                    } else {
                        featureCampaignBackArrow.alpha = 0.5f
                    }
                }
            }
        }
        recyclerViewFeatureCampaign.addOnScrollListener(scrollListener)

        //next button click
        featureCampaignFrontArrow.setOnClickListener {
            //deviceNameValue.layoutManager.smoothScrollToPosition(deviceNameValue.layoutManager.)
            val count = recyclerViewFeatureCampaign.adapter!!.itemCount
            Log.e(TAG, "" + count)
            val pos = position + 1
            val cnt = count - 1
            Log.e(TAG, "$pos  $cnt")
            if (pos <= cnt) {
                featureCampaignFrontArrow.alpha = 1.0f
                recyclerViewFeatureCampaign.smoothScrollToPosition(position + 1)
            } else {
                featureCampaignFrontArrow.alpha = 0.5f
            }

        }

        //back button click
        featureCampaignBackArrow.setOnClickListener {

            if ((position - 1) > -1) {
                featureCampaignBackArrow.alpha = 1.0f
                recyclerViewFeatureCampaign.smoothScrollToPosition(position - 1)
            } else {
                featureCampaignBackArrow.alpha = 0.5f
            }
        }

        val scrollListenerNpo = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerViewNpoCampaign.computeHorizontalScrollOffset()
                val cellWidth = recyclerViewNpoCampaign.getChildAt(0).measuredWidth
                if (offset % cellWidth == 0) {
                    positionNpo = offset / cellWidth

                    val count = recyclerViewNpoCampaign.adapter!!.itemCount
                    Log.e(TAG, " $count")
                    //next button
                    if ((positionNpo + 1) < (count - 1)) {
                        featureCampaignFrontArrowNpo.alpha = 1.0f
                    } else {

                        featureCampaignFrontArrowNpo.alpha = 0.5f
                    }

                    //back button
                    if ((positionNpo - 1) > -1) {
                        featureCampaignBackArrowNpo.alpha = 1.0f
                    } else {
                        featureCampaignBackArrowNpo.alpha = 0.5f
                    }
                }
            }
        }
        recyclerViewNpoCampaign.addOnScrollListener(scrollListenerNpo)

        //next button click
        featureCampaignFrontArrowNpo.setOnClickListener {
            //deviceNameValue.layoutManager.smoothScrollToPosition(deviceNameValue.layoutManager.)
            val count = recyclerViewNpoCampaign.adapter!!.itemCount
            val pos = positionNpo + 1
            val cnt = count - 1
            if (pos <= cnt) {
                featureCampaignFrontArrowNpo.alpha = 1.0f
                recyclerViewNpoCampaign.smoothScrollToPosition(positionNpo + 1)
            } else {
                featureCampaignFrontArrowNpo.alpha = 0.5f
            }

        }

        //back button click
        featureCampaignBackArrowNpo.setOnClickListener {

            if ((positionNpo - 1) > -1) {
                featureCampaignBackArrowNpo.alpha = 1.0f
                recyclerViewNpoCampaign.smoothScrollToPosition(positionNpo - 1)
            } else {
                featureCampaignBackArrowNpo.alpha = 0.5f
            }
        }

        val scrollListenerpc = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerViewPublicCommunityCampaign.computeHorizontalScrollOffset()
                val cellWidth = recyclerViewPublicCommunityCampaign.getChildAt(0).measuredWidth
                if (offset % cellWidth == 0) {
                    positionPc = offset / cellWidth

                    val count = recyclerViewPublicCommunityCampaign.adapter!!.itemCount
                    Log.e(TAG, " $count")
                    //next button
                    if ((positionPc + 1) < (count - 1)) {
                        featureCampaignFrontArrowPC.alpha = 1.0f
                    } else {

                        featureCampaignFrontArrowPC.alpha = 0.5f
                    }

                    //back button
                    if ((positionPc - 1) > -1) {
                        featureCampaignBackArrowPC.alpha = 1.0f
                    } else {
                        featureCampaignBackArrowPC.alpha = 0.5f
                    }
                }
            }
        }
        recyclerViewPublicCommunityCampaign.addOnScrollListener(scrollListenerpc)

        //next button click
        featureCampaignFrontArrowPC.setOnClickListener {
            //deviceNameValue.layoutManager.smoothScrollToPosition(deviceNameValue.layoutManager.)
            val count = recyclerViewPublicCommunityCampaign.adapter!!.itemCount
            Log.e(TAG, "" + count)
            val pos = positionPc + 1
            val cnt = count - 1
            if (pos <= cnt) {
                featureCampaignFrontArrowPC.alpha = 1.0f
                recyclerViewPublicCommunityCampaign.smoothScrollToPosition(positionPc + 1)
            } else {
                featureCampaignFrontArrowPC.alpha = 0.5f
            }

        }

        //back button click
        featureCampaignBackArrowPC.setOnClickListener {

            if ((positionPc - 1) > -1) {
                featureCampaignBackArrowPC.alpha = 1.0f
                recyclerViewPublicCommunityCampaign.smoothScrollToPosition(positionPc - 1)
            } else {
                featureCampaignBackArrowPC.alpha = 0.5f
            }
        }

    }


}
