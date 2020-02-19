package com.ahe.ui.auth.advertiser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ahe.R
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_webstore_sign_up_intro.*

class WebstoreSignUpIntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webstore_sign_up_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBtn.setOnClickListener {
            navSignUpFirstPage()
        }

        loginLabel.setOnClickListener {
            navLauncher()
        }
    }

    private fun navSignUpFirstPage() {
        findNavController().navigate(R.id.action_signupAsMemberFragment_to_signupAsMemberFirstFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_advertiserIntroFragment_to_loginFragment)
    }


}

