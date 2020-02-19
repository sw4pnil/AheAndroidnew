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
import com.ahe.ui.auth.viewmodel.getSignUpForAdvertiserScreen
import com.ahe.ui.auth.viewmodel.setSignUpForAdvertiserFragment
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_webstore_sign_up_second_page.*


class WebstoreSignUpSecondPageFragment : BaseAuthFragment() {

    private var getSignUpForAdvertiser: SignUpForAdvertiserRegister? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webstore_sign_up_second_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        getSignUpForAdvertiser = viewModel.getSignUpForAdvertiserScreen()

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
        findNavController().navigate(R.id.action_advertiserSecondFragment_to_backFirstFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_advertiserSecondFragment_to_chooseRoleFragment)
    }


    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsAdvertiserFields?.let { it1 ->
                it1.registration_ein.let { it2 -> einAsAdvertiserEditText.setText(it2) }
                it1.registration_category.let { categoriesSpinner.selectedItemId + 1 }
                it1.registration_image.let { "" }
                it1.registration_about.let { it2 -> abtEditText.setText(it2) }
            }
        })


    }


    private fun navSecondSignUpPage() {

        val registration = SignUpForAdvertiserRegister(
            0,
            getSignUpForAdvertiser?.name.toString(),
            getSignUpForAdvertiser?.cname.toString(),
            getSignUpForAdvertiser?.phone.toString(),
            getSignUpForAdvertiser?.website.toString(),
            getSignUpForAdvertiser?.email.toString(),
            einAsAdvertiserEditText.text.toString(),
            (categoriesSpinner.selectedItemId + 1).toString(),
            "",
            abtEditText.text.toString()
        )

        viewModel.setSignUpForAdvertiserFragment(registration)

        if (einAsAdvertiserEditText.text.toString().isNotEmpty()
            && abtEditText.text.toString().isNotEmpty()
        ) {
            findNavController().navigate(R.id.action_advertiserSecondFragment_to_advertiserThirdPageFragment)
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
                einAsAdvertiserEditText.text.toString(),
                abtEditText.text.toString()
            )
        )
    }
}
