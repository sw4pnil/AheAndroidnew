package com.ahe.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ahe.R
import com.ahe.constants.USERTYPE
import com.ahe.ui.auth.state.AuthStateEvent.LoginAttemptEvent
import com.ahe.ui.auth.state.LoginFields
import com.ahe.ui.auth.viewmodel.getUserType
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseAuthFragment() {

    lateinit var role: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "LoginFragment: $viewModel")
        subscribeObservers()

        val userType = viewModel.getUserType()
        if (userType == USERTYPE.DONOR) role = "1"
        if (userType == USERTYPE.WEBSTORE) role = "3"
        if (userType == USERTYPE.NPO) role = "4"
        if (userType == USERTYPE.PUBLIC_COMMUNITY) role = "5"
        if (userType == USERTYPE.GUEST) role = "0"

        loginBtn.setOnClickListener {
            this.login(role)
        }
    }

    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.loginFields?.let { it1 ->
                it1.login_email?.let { it2 -> emailEditText.setText(it2) }
                it1.login_password?.let { it2 -> passwordEdit.setText(it2) }
            }
        })
    }

    private fun login(user_type: String?) {
        viewModel.setStateEvent(
            LoginAttemptEvent(
                emailEditText.text.toString(),
                passwordEdit.text.toString(),
                user_type!!
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setLoginFields(
            LoginFields(
                emailEditText.text.toString(),
                passwordEdit.text.toString()
            )
        )
    }

}