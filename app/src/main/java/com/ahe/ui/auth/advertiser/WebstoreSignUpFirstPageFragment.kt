package com.ahe.ui.auth.advertiser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahe.R
import com.ahe.ui.*
import com.ahe.ui.auth.BaseAuthFragment
import com.ahe.ui.auth.state.SignUpAsAdvertiserFields
import com.ahe.ui.auth.viewmodel.SignUpForAdvertiserRegister
import com.ahe.ui.auth.viewmodel.setSignUpForAdvertiserFirstFragment
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_webstore_sign_up_first_page.*

class WebstoreSignUpFirstPageFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webstore_sign_up_first_page, container, false)
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
        findNavController().navigate(R.id.action_advertiserFirstFragment_to_backIntroFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_advertiserFirstFragment_to_loginFragment)
    }


    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsAdvertiserFields?.let { it1 ->
                it1.registration_name.let { it2 -> nameAsAdvertiserEditText.setText(it2) }
                it1.registration_cName.let { it2 -> contactNameAsAdvertiserEditText.setText(it2) }
                it1.registration_phone.let { it2 -> phoneAdvertiserEditText.setText(it2) }
                it1.registration_website.let { it2 -> websiteEditText.setText(it2) }
                it1.registration_email.let { it2 -> emailEditText.setText(it2) }
            }
        })


    }


    private fun navSecondSignUpPage() {

        val registration = SignUpForAdvertiserRegister(
            0,
            nameAsAdvertiserEditText.text.toString(),
            contactNameAsAdvertiserEditText.text.toString(),
            phoneAdvertiserEditText.text.toString(),
            websiteEditText.text.toString(),
            emailEditText.text.toString()
        )

        viewModel.setSignUpForAdvertiserFirstFragment(registration)

        if (nameAsAdvertiserEditText.text.toString().isNotEmpty()
            && contactNameAsAdvertiserEditText.text.toString().isNotEmpty()
            && emailEditText.text.toString().isNotEmpty()
            && phoneAdvertiserEditText.text.toString().isNotEmpty()
        ) {
            findNavController().navigate(R.id.action_advertiserFirstFragment_to_advertiserSecondPageFragment)
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
        viewModel.setSignUpForAdvertiserRegistrationFields(
            SignUpAsAdvertiserFields(
                nameAsAdvertiserEditText.text.toString(),
                contactNameAsAdvertiserEditText.text.toString(),
                phoneAdvertiserEditText.text.toString(),
                emailEditText.text.toString(),
                websiteEditText.text.toString()

            )
        )
    }

}

