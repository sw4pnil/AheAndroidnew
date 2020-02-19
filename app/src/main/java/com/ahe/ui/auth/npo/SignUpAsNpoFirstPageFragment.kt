package com.ahe.ui.auth.npo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.*
import com.ahe.ui.auth.BaseAuthFragment
import com.ahe.ui.auth.state.SignUpAsNpoFirstPageFields
import com.ahe.ui.auth.viewmodel.SignUpForNpoRegister
import com.ahe.ui.auth.viewmodel.setSignUpForNpoFirstFragment
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_npo_first_page.*

class SignUpAsNpoFirstPageFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_as_npo_first_page, container, false)
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
        findNavController().navigate(R.id.action_signupAsNpoFirstFragment_to_signUpNpoIntroFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_signupAsNpoFirstFragment_to_chooseRoleFragment)
    }


    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsNpoFirstPageFields?.let { it1 ->
                it1.registration_name?.let { it2 -> nameAsNpoEditText.setText(it2) }
                it1.registration_cName?.let { it2 -> contactNameAsNpoEditText.setText(it2) }
                it1.registration_email?.let { it2 -> emailEditText.setText(it2) }
                it1.registration_website?.let { it2 -> websiteEditText.setText(it2) }
                it1.registration_ein?.let { it2 -> einAsNpoEditText.setText(it2) }
            }
        })


    }


    private fun navSecondSignUpPage() {

        val registration = SignUpForNpoRegister(
            0,
            nameAsNpoEditText.text.toString(),
            contactNameAsNpoEditText.text.toString(),
            emailEditText.text.toString(),
            websiteEditText.text.toString(),
            einAsNpoEditText.text.toString(),"","",""
        )

        viewModel.setSignUpForNpoFirstFragment(registration)

        if (nameAsNpoEditText.text.toString().isNotEmpty()
            && contactNameAsNpoEditText.text.toString().isNotEmpty()
            && emailEditText.text.toString().isNotEmpty()
            && websiteEditText.text.toString().isNotEmpty()
            && einAsNpoEditText.text.toString().isNotEmpty()
        ) {
            findNavController().navigate(R.id.action_signupAsNpoFirstFragment_to_signUpNpoSecondFragment)
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
        viewModel.setSignUpForNpoRegistrationFields(
            SignUpAsNpoFirstPageFields(
                nameAsNpoEditText.text.toString(),
                contactNameAsNpoEditText.text.toString(),
                emailEditText.text.toString(),
                websiteEditText.text.toString(),
                einAsNpoEditText.text.toString()
            )
        )
    }

}
