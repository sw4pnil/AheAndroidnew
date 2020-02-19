package com.ahe.ui.auth.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.auth.BaseAuthFragment
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_member.*

class SignUpAsMemberFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up_as_member, container, false)
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
        findNavController().navigate(R.id.action_signupAsMemberFragment_to_chooseRoleFragment)
    }


}
