package com.ahe.ui.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.ahe.R
import com.ahe.ui.UIMessage
import com.ahe.ui.UIMessageType
import com.ahe.ui.UserTypeCallback
import kotlinx.android.synthetic.main.fragment_launcher.*

class LauncherFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donorLayout.setOnClickListener {
            navDonor()
        }

        startCampaignLayout.setOnClickListener {
            val callback: UserTypeCallback = object : UserTypeCallback {
                override fun proceed(userType: String) {
                    if(userType == "1"){
                        navCampaign()
                    }
                    else if(userType == "2"){
                        navCampaign()
                    }
                }
            }

            uiCommunicationListener.onUIMessageReceived(
                UIMessage(
                    "",
                    UIMessageType.UserTypeCallback(callback)
                )
            )
        }

        advertiseLayout.setOnClickListener {
            navAdvertiser()
        }

    }


    fun navDonor() {
        findNavController().navigate(R.id.action_launcherFragment_to_donorLoginFragment)
    }

    fun navCampaign() {
        findNavController().navigate(R.id.action_launcherFragment_to_campaignLoginFragment)
    }

    fun navAdvertiser() {
        findNavController().navigate(R.id.action_launcherFragment_to_advertiserLoginFragment)
    }

}





















