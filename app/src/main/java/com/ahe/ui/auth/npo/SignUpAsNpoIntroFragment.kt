package com.ahe.ui.auth.npo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.auth.BaseAuthFragment
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_member.*

class SignUpAsNpoIntroFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_as_npo_intro, container, false)
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
        findNavController().navigate(R.id.action_signupAsNpoFragment_to_signupAsNpoFirstFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_signupAsNpoFragment_to_chooseRoleFragment)
    }


}
