package com.ahe.ui.auth.advertiser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.auth.BaseAuthFragment
import com.ahe.ui.auth.state.AuthStateEvent
import com.ahe.ui.auth.state.SignUpAsAdvertiserFields
import com.ahe.ui.auth.viewmodel.SignUpForAdvertiserRegister
import com.ahe.ui.auth.viewmodel.getSignUpForAdvertiserScreen
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_webstore_sign_up_third_page.*
import kotlinx.android.synthetic.main.sign_up_with_social.*

class WebstoreSignUpThirdPageFragment : BaseAuthFragment() {

    private var getSignUpForAdvertiserRegister: SignUpForAdvertiserRegister? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webstore_sign_up_third_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        getSignUpForAdvertiserRegister = viewModel.getSignUpForAdvertiserScreen()

        signUpButton.setOnClickListener {
            this.signUpThirdPage()
        }
        backText.setOnClickListener {
            backFragment()
        }
        backArrow.setOnClickListener {
            backFragment()
        }
        loginLabel.setOnClickListener {
            navLauncher()
        }
    }

    private fun backFragment() {
        findNavController().navigate(R.id.action_advertiserThirdFragment_to_backSecondFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_advertiserThirdFragment_to_chooseRoleFragment)
    }

    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsAdvertiserFields?.let { it1 ->
                it1.registration_password?.let { it2 -> passwordEdit.setText(it2) }
                it1.registration_confirm_password?.let { it2 -> confirmPasswordEdit.setText(it2) }
            }
        })
    }

    private fun signUpThirdPage() {
        viewModel.setStateEvent(
            AuthStateEvent.SignUpAsAdvertiserAttemptEvent(
                getSignUpForAdvertiserRegister?.name.toString(),
                getSignUpForAdvertiserRegister?.cname.toString(),
                getSignUpForAdvertiserRegister?.email.toString(),
                getSignUpForAdvertiserRegister?.website.toString(),
                getSignUpForAdvertiserRegister?.ein.toString(),
                getSignUpForAdvertiserRegister?.phone.toString(),
                getSignUpForAdvertiserRegister?.about.toString(),
                "",
                passwordEdit.text.toString(),
                confirmPasswordEdit.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setSignUpForAdvertiserRegistrationFields(
            SignUpAsAdvertiserFields(
                passwordEdit.text.toString(),
                confirmPasswordEdit.text.toString()
            )
        )
    }
}

