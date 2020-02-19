package com.ahe.ui.auth.npo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.auth.BaseAuthFragment
import com.ahe.ui.auth.state.AuthStateEvent
import com.ahe.ui.auth.state.SignUpAsNpoFirstPageFields
import com.ahe.ui.auth.viewmodel.SignUpForNpoRegister
import com.ahe.ui.auth.viewmodel.getSignUpForNpoFirstScreen
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_member_second.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_member_second.backArrow
import kotlinx.android.synthetic.main.fragment_sign_up_as_member_second.backText
import kotlinx.android.synthetic.main.sign_up_with_social.*

class SignUpAsNpoThirdPageFragment : BaseAuthFragment() {

    private var getSignUpForNpoFirstScreen: SignUpForNpoRegister? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_as_npo_third_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        getSignUpForNpoFirstScreen = viewModel.getSignUpForNpoFirstScreen()

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
        findNavController().navigate(R.id.action_signupAsMemberSecondFragment_to_signUpFirstFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_signupAsMemberSecondFragment_to_chooseRoleFragment)
    }

    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsMemberFirstPageFields?.let { it1 ->
                it1.registration_password?.let { it2 -> passwordEdit.setText(it2) }
                it1.registration_confirm_password?.let { it2 -> confirmPasswordEdit.setText(it2) }
            }
        })
    }

    private fun signUpThirdPage() {
        viewModel.setStateEvent(
            AuthStateEvent.SignUpAsNpoAttemptEvent(
                getSignUpForNpoFirstScreen?.name.toString(),
                getSignUpForNpoFirstScreen?.cname.toString(),
                getSignUpForNpoFirstScreen?.email.toString(),
                getSignUpForNpoFirstScreen?.website.toString(),
                getSignUpForNpoFirstScreen?.ein.toString(),
                getSignUpForNpoFirstScreen?.phone.toString(),
                getSignUpForNpoFirstScreen?.address.toString(),
                getSignUpForNpoFirstScreen?.about.toString(),
                "",
                passwordEdit.text.toString(),
                confirmPasswordEdit.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setSignUpForNpoRegistrationFields(
            SignUpAsNpoFirstPageFields(
                passwordEdit.text.toString(),
                confirmPasswordEdit.text.toString()
            )
        )
    }
}


