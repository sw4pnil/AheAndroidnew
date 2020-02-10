package com.ahe.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.constants.USERTYPE
import com.ahe.ui.UIMessage
import com.ahe.ui.UIMessageType
import com.ahe.ui.UserTypeCallback
import com.ahe.ui.auth.viewmodel.setUserType
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_choose_role.*


class ChooseRoleFragment : BaseAuthFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_role, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        donorLayout.setOnClickListener {
            navDonor()
        }

        startCampaignLayout.setOnClickListener {
            val callback: UserTypeCallback = object : UserTypeCallback {
                override fun proceed(userType: USERTYPE) {
                    if (userType == USERTYPE.NPO) {
                        navCampaign(userType)
                    } else if (userType == USERTYPE.PUBLIC_COMMUNITY) {
                        navCampaign(userType)
                    }
                }
            }

            uiCommunicationListener.onUIMessageReceived(
                UIMessage(
                    "",
                    UIMessageType.UserTypeDialog(callback)
                )
            )
        }

        advertiseLayout.setOnClickListener {
            navAdvertiser()
        }

        guestLayout.setOnClickListener {
            navGuest()
        }

        loginLabel.setOnClickListener {
            navLauncher()
        }

    }

    private fun navDonor() {
        viewModel.setUserType(USERTYPE.DONOR)
        findNavController().navigate(R.id.action_chooseRoleFragment_to_donorSignUpFragment)
    }

    fun navCampaign(selection: USERTYPE) {
        viewModel.setUserType(selection)
        findNavController().navigate(R.id.action_chooseRoleFragment_to_campaignSignUpFragment)
    }

    private fun navAdvertiser() {
        viewModel.setUserType(USERTYPE.WEBSTORE)
        findNavController().navigate(R.id.action_chooseRoleFragment_to_advertiserSignUpFragment)
    }

    private fun navGuest() {
        //go back to home screen
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_chooseRoleFragment_to_launcherFragment)
    }


}
