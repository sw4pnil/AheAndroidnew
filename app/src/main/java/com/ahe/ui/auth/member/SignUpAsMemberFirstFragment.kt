package com.ahe.ui.auth.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.*
import com.ahe.ui.auth.BaseAuthFragment
import com.ahe.ui.auth.state.SignUpAsMemberFirstPageFields
import com.ahe.ui.auth.viewmodel.SignUpForMemberRegister
import com.ahe.ui.auth.viewmodel.setSignUpForMemberFirstFragment
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_member_first.*


class SignUpAsMemberFirstFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_as_member_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        nextBtn.setOnClickListener {
            navSecondSignUpPage()
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
        findNavController().navigate(R.id.action_signupAsMemberFragment_to_signupAsMemberFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_signupAsMemberFirstFragment_to_chooseRoleFragment)
    }


    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsMemberFirstPageFields?.let { it1 ->
                it1.registration_firstName?.let { it2 -> firstNameAsMemberEditText.setText(it2) }
                it1.registration_lastName?.let { it2 -> lastNameAsMemberEditText.setText(it2) }
                it1.registration_email?.let { it2 -> emailEditText.setText(it2) }
                it1.registration_country_id?.let { countriesSpinner.selectedItemId + 1 }
                it1.registration_phone?.let { it2 -> mobileNumberEditText.setText(it2) }
            }
        })


    }


    private fun navSecondSignUpPage() {

        val registration = SignUpForMemberRegister(
            0,
            firstNameAsMemberEditText.text.toString(),
            lastNameAsMemberEditText.text.toString(),
            emailEditText.text.toString(),
            (countriesSpinner.selectedItemId + 1).toString(),
            "",
            mobileNumberEditText.text.toString()
        )

        viewModel.setSignUpForMemberFirstFragment(registration)

        if (firstNameAsMemberEditText.text.toString().isNotEmpty()
            && lastNameAsMemberEditText.text.toString().isNotEmpty()
            && emailEditText.text.toString().isNotEmpty()
            && mobileNumberEditText.text.toString().isNotEmpty()
        ) {
            findNavController().navigate(R.id.action_signupAsMemberFragment_to_signupAsMemberSecondFragment)
        } else {
            // return "All fields are required."
            showErrorDialog(getString(R.string.fields_require))
        }

    }

    private fun showErrorDialog(errorMessage: String) {
        stateChangeListener.onDataStateChange(
            DataState(
                Event(StateError(Response(errorMessage, ResponseType.Dialog()))),
                Loading(isLoading = false),
                Data(Event.dataEvent(null), null)
            )
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setSignUpForMemberRegistrationFields(
            SignUpAsMemberFirstPageFields(
                firstNameAsMemberEditText.text.toString(),
                lastNameAsMemberEditText.text.toString(),
                emailEditText.text.toString(),
                (countriesSpinner.selectedItemId + 1).toString(),
                mobileNumberEditText.text.toString()
            )
        )
    }

}
