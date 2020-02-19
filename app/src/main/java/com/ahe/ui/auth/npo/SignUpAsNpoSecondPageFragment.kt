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
import com.ahe.ui.auth.viewmodel.*
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlinx.android.synthetic.main.fragment_sign_up_as_npo_second_page.*

class SignUpAsNpoSecondPageFragment : BaseAuthFragment() {

    private var getSignUpForNpoFirstScreen: SignUpForNpoRegister? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_as_npo_second_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        getSignUpForNpoFirstScreen = viewModel.getSignUpForNpoFirstScreen()

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
        findNavController().navigate(R.id.action_signupAsNpoSecondFragment_to_signUpNpoFirstFragment)
    }

    private fun navLauncher() {
        findNavController().navigate(R.id.action_signupAsNpoSecondFragment_to_chooseRoleFragment)
    }


    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.signUpAsNpoFirstPageFields?.let { it1 ->
                it1.registration_phone?.let { it2 -> phoneNpoEditText.setText(it2) }
                it1.registration_address?.let { it2 -> addressNpoEditText.setText(it2) }
                it1.registration_about?.let { it2 -> abtEditText.setText(it2) }
                it1.registration_image?.let { it2 -> /*uploadNpoImg.setText(it2)*/ }
            }
        })


    }


    private fun navSecondSignUpPage() {

        val registration = SignUpForNpoRegister(
            0,
            getSignUpForNpoFirstScreen?.name.toString(),
            getSignUpForNpoFirstScreen?.cname.toString(),
            getSignUpForNpoFirstScreen?.email.toString(),
            getSignUpForNpoFirstScreen?.website.toString(),
            getSignUpForNpoFirstScreen?.ein.toString(),
            phoneNpoEditText.text.toString(),
            addressNpoEditText.text.toString(),
            abtEditText.text.toString()
        )

        viewModel.setSignUpForNpoSecondFragment(registration)

        if (phoneNpoEditText.text.toString().isNotEmpty()
            && addressNpoEditText.text.toString().isNotEmpty()
            && abtEditText.text.toString().isNotEmpty()
        ) {
            findNavController().navigate(R.id.action_signupAsNpoSecondFragment_to_signUpThirdFragment)
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
                phoneNpoEditText.text.toString(),
                addressNpoEditText.text.toString(),
                abtEditText.text.toString()
            )
        )
    }
}
