package com.ahe.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.auth.state.AuthStateEvent.SignUpAsMemberFirestPageAttemptEvent
import com.ahe.ui.auth.state.SignUpAsMemberFirstPageFields
import com.ahe.ui.auth.viewmodel.SignUpForMemberRegister
import com.ahe.ui.auth.viewmodel.getSignUpForMemberFirstScreen
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_member_second.*
import kotlinx.android.synthetic.main.sign_up_with_social.*


class SignUpAsMemberSecondFragment : BaseAuthFragment() {

    private var getSignUpForMemberFirstScreen: SignUpForMemberRegister? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_as_member_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        getSignUpForMemberFirstScreen = viewModel.getSignUpForMemberFirstScreen()

        signUpButton.setOnClickListener {
            this.signUpSecondPage()
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

    private fun signUpSecondPage() {
        viewModel.setStateEvent(
            SignUpAsMemberFirestPageAttemptEvent(
                getSignUpForMemberFirstScreen?.fname.toString(),
                getSignUpForMemberFirstScreen?.lname.toString(),
                getSignUpForMemberFirstScreen?.email.toString(),
                getSignUpForMemberFirstScreen?.country_id.toString(),
                getSignUpForMemberFirstScreen?.image.toString(),
                getSignUpForMemberFirstScreen?.phone.toString(),
                passwordEdit.text.toString(),
                confirmPasswordEdit.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setSignUpForMemberRegistrationFields(
            SignUpAsMemberFirstPageFields(
                passwordEdit.text.toString(),
                confirmPasswordEdit.text.toString()
            )
        )
    }
}
