package com.ahe.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ahe.R
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_read_more.*

class ReadMoreFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeButton.setOnClickListener {
            navChooseRole()
        }

        loginLabel.setOnClickListener {
            navLauncher()
        }

        donateButton.setOnClickListener {
            navDonate()
        }

        closeDialogue.setOnClickListener {
            //nav to home
        }
    }

    private fun navChooseRole() {
        findNavController().navigate(R.id.action_readMoreFragment_to_chooseRoleFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_readMoreFragment_to_launcherFragment)
    }

    private fun navDonate() {
        findNavController().navigate(R.id.action_readMoreFragment_to_donateFragment)

    }
}
