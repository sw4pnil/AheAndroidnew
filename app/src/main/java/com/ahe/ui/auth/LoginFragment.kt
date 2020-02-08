package com.ahe.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ahe.R
import com.ahe.ui.auth.state.AuthStateEvent.LoginAttemptEvent
import com.ahe.ui.auth.state.LoginFields
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseAuthFragment() {

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
        loginBtn.setOnClickListener {
            login()
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

    fun login() {
        viewModel.setStateEvent(
            LoginAttemptEvent(
                emailEditText.text.toString(),
                passwordEdit.text.toString(),
                "1"
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