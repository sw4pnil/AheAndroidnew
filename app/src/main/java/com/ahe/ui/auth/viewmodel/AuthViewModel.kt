package com.ahe.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ahe.models.AuthToken
import com.ahe.repository.auth.AuthRepository
import com.ahe.ui.BaseViewModel
import com.ahe.ui.DataState
import com.ahe.ui.auth.state.*
import com.ahe.ui.auth.state.AuthStateEvent.*
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<AuthStateEvent, AuthViewState>() {
    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        when (stateEvent) {
            is LoginAttemptEvent -> {
                return authRepository.attemptLogin(
                    stateEvent.email,
                    stateEvent.password,
                    stateEvent.role
                )
            }

            is RegisterAttemptEvent -> {
                return authRepository.attemptRegistration(
                    stateEvent.email,
                    stateEvent.username,
                    stateEvent.password,
                    stateEvent.confirm_password
                )
            }

            is CheckPreviousAuthEvent -> {
                return authRepository.checkPreviousAuthUser()
            }


            is None -> {
                return liveData {
                }
            }
            is SignUpAsMemberFirestPageAttemptEvent -> {
                return authRepository.attemptSignUpForMember(
                    stateEvent.fname,
                    stateEvent.lname,
                    stateEvent.email,
                    stateEvent.country_id,
                    stateEvent.image,
                    stateEvent.phone,
                    stateEvent.password,
                    stateEvent.confirm_password
                )
            }

            is SignUpAsNpoAttemptEvent -> {
                return authRepository.attemptSignUpForNpo(
                    stateEvent.name,
                    stateEvent.cname,
                    stateEvent.email,
                    stateEvent.website,
                    stateEvent.ein,
                    stateEvent.phone,
                    stateEvent.address,
                    stateEvent.about,
                    stateEvent.image,
                    stateEvent.password,
                    stateEvent.confirm_password
                )
            }
            else -> {
                return liveData {
                }
            }
        }
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    fun setRegistrationFields(registrationFields: RegistrationFields) {
        val update = getCurrentViewStateOrNew()
        if (update.registrationFields == registrationFields) {
            return
        }
        update.registrationFields = registrationFields
        setViewState(update)
    }

    fun setSignUpForMemberRegistrationFields(registrationFields: SignUpAsMemberFirstPageFields) {
        val update = getCurrentViewStateOrNew()
        if (update.signUpAsMemberFirstPageFields == registrationFields) {
            return
        }
        update.signUpAsMemberFirstPageFields = registrationFields
        setViewState(update)
    }

    fun setSignUpForNpoRegistrationFields(registrationFields: SignUpAsNpoFirstPageFields) {
        val update = getCurrentViewStateOrNew()
        if (update.signUpAsNpoFirstPageFields == registrationFields) {
            return
        }
        update.signUpAsNpoFirstPageFields = registrationFields
        setViewState(update)
    }

    fun setSignUpForAdvertiserRegistrationFields(registrationFields: SignUpAsAdvertiserFields) {
        val update = getCurrentViewStateOrNew()
        if (update.signUpAsAdvertiserFields == registrationFields) {
            return
        }
        update.signUpAsAdvertiserFields = registrationFields
        setViewState(update)
    }
    fun setLoginFields(loginFields: LoginFields) {
        val update = getCurrentViewStateOrNew()
        if (update.loginFields == loginFields) {
            return
        }
        update.loginFields = loginFields
        setViewState(update)
    }

    fun setAuthToken(authToken: AuthToken) {
        val update = getCurrentViewStateOrNew()
        if (update.authToken == authToken) {
            return
        }
        update.authToken = authToken
        setViewState(update)
    }

    fun cancelActiveJobs() {
        handlePendingData()
        authRepository.cancelActiveJobs()
    }

    fun handlePendingData() {
        setStateEvent(None())
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }
}