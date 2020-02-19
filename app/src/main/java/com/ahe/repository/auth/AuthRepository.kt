package com.ahe.repository.auth

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.ahe.api.auth.OpenApiAuthService
import com.ahe.api.auth.network_responses.LoginResponse
import com.ahe.api.auth.network_responses.RegistrationResponse
import com.ahe.models.AccountProperties
import com.ahe.models.AuthToken
import com.ahe.persistence.AccountPropertiesDao
import com.ahe.persistence.AuthTokenDao
import com.ahe.repository.JobManager
import com.ahe.repository.NetworkBoundResource
import com.ahe.session.SessionManager
import com.ahe.ui.DataState
import com.ahe.ui.Response
import com.ahe.ui.ResponseType
import com.ahe.ui.auth.state.*
import com.ahe.util.AbsentLiveData
import com.ahe.util.ApiSuccessResponse
import com.ahe.util.ErrorHandling.Companion.ERROR_SAVE_ACCOUNT_PROPERTIES
import com.ahe.util.ErrorHandling.Companion.ERROR_SAVE_AUTH_TOKEN
import com.ahe.util.ErrorHandling.Companion.GENERIC_AUTH_ERROR
import com.ahe.util.GenericApiResponse
import com.ahe.util.PreferenceKeys
import com.ahe.util.SuccessHandling.Companion.RESPONSE_CHECK_PREVIOUS_AUTH_USER_DONE
import kotlinx.coroutines.Job
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager,
    private val sharedPreferences: SharedPreferences,
    private val sharedPrefsEditor: SharedPreferences.Editor
) : JobManager("AuthRepository") {

    private val TAG: String = "AppDebug"

    fun attemptLogin(
        email: String,
        password: String,
        role: String
    ): LiveData<DataState<AuthViewState>> {

        val loginFieldErrors = LoginFields(email, password).isValidForLogin()
        if (loginFieldErrors != LoginFields.LoginError.none()) {
            return returnErrorResponse(loginFieldErrors, ResponseType.Dialog())
        }

        return object : NetworkBoundResource<LoginResponse, Any, AuthViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ) {

            // Ignore
            override fun loadFromCache(): LiveData<AuthViewState> {
                return AbsentLiveData.create()
            }

            // Ignore
            override suspend fun updateLocalDb(cacheObject: Any?) {

            }

            // not used in this case
            override suspend fun createCacheRequestAndReturn() {

            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<LoginResponse>) {
                Log.d(TAG, "handleApiSuccessResponse: $response")

                // Incorrect login credentials counts as a 200 response from server, so need to handle that
                if (response.body.response == GENERIC_AUTH_ERROR) {
                    return onErrorReturn(
                        response.body.errorMessage,
                        shouldUseDialog = true,
                        shouldUseToast = false
                    )
                }

                // Don't care about result here. Just insert if it doesn't exist b/c of foreign key relationship
                // with AuthToken
                accountPropertiesDao.insertOrIgnore(
                    AccountProperties(
                        response.body.data.id ?: 0,
                        response.body.data.email ?: "",
                        ""
                    )
                )

                // will return -1 if failure
                val result = authTokenDao.insert(
                    AuthToken(
                        response.body.data.id ?: 0,
                        response.body.token
                    )
                )
                if (result < 0) {
                    return onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_AUTH_TOKEN, ResponseType.Dialog())
                        )
                    )
                }

                saveAuthenticatedUserToPrefs(email)

                onCompleteJob(
                    DataState.data(
                        data = AuthViewState(
                            authToken = AuthToken(response.body.data.id ?: 0, response.body.token)
                        )
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<LoginResponse>> {
                return openApiAuthService.login(email, password, role)
            }

            override fun setJob(job: Job) {
                addJob("attemptLogin", job)
            }

        }.asLiveData()
    }

    fun attemptRegistration(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>> {

        val registrationFieldErrors =
            RegistrationFields(email, username, password, confirmPassword).isValidForRegistration()
        if (registrationFieldErrors != RegistrationFields.RegistrationError.none()) {
            return returnErrorResponse(registrationFieldErrors, ResponseType.Dialog())
        }

        return object : NetworkBoundResource<RegistrationResponse, Any, AuthViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ) {
            // Ignore
            override fun loadFromCache(): LiveData<AuthViewState> {
                return AbsentLiveData.create()
            }

            // Ignore
            override suspend fun updateLocalDb(cacheObject: Any?) {

            }

            // not used in this case
            override suspend fun createCacheRequestAndReturn() {

            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<RegistrationResponse>) {

                Log.d(TAG, "handleApiSuccessResponse: $response")

                if (response.body.response == GENERIC_AUTH_ERROR) {
                    return onErrorReturn(
                        response.body.errorMessage,
                        shouldUseDialog = true,
                        shouldUseToast = false
                    )
                }

                val result1 = accountPropertiesDao.insertAndReplace(
                    AccountProperties(
                        response.body.data.id ?: 0,
                        response.body.data.email ?: "",
                        response.body.data.name ?: ""
                    )
                )

                // will return -1 if failure
                if (result1 < 0) {
                    onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_ACCOUNT_PROPERTIES, ResponseType.Dialog())
                        )
                    )
                    return
                }

                // will return -1 if failure
                val result2 = authTokenDao.insert(
                    AuthToken(
                        response.body.data.id ?: 0,
                        response.body.token
                    )
                )
                if (result2 < 0) {
                    onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_AUTH_TOKEN, ResponseType.Dialog())
                        )
                    )
                    return
                }

                saveAuthenticatedUserToPrefs(email)

                onCompleteJob(
                    DataState.data(
                        data = AuthViewState(
                            authToken = AuthToken(response.body.data.id ?: 0, response.body.token)
                        )
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<RegistrationResponse>> {
                return openApiAuthService.register(email, username, password, confirmPassword)
            }

            override fun setJob(job: Job) {
                addJob("attemptRegistration", job)
            }

        }.asLiveData()
    }


    fun attemptSignUpForMember(
        fname: String,
        lname: String,
        email: String,
        country_id: String,
        image: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>> {

        val registrationFieldErrors = SignUpAsMemberFirstPageFields(
            fname,
            lname,
            email,
            country_id,
            image,
            phone,
            password,
            confirmPassword
        ).isValidForRegistration()
        if (registrationFieldErrors != SignUpAsMemberFirstPageFields.RegistrationError.none()) {
            return returnErrorResponse(registrationFieldErrors, ResponseType.Dialog())
        }

        return object : NetworkBoundResource<RegistrationResponse, Any, AuthViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ) {
            // Ignore
            override fun loadFromCache(): LiveData<AuthViewState> {
                return AbsentLiveData.create()
            }

            // Ignore
            override suspend fun updateLocalDb(cacheObject: Any?) {

            }

            // not used in this case
            override suspend fun createCacheRequestAndReturn() {

            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<RegistrationResponse>) {

                Log.d(TAG, "handleApiSuccessResponse: $response")

                if (response.body.response == GENERIC_AUTH_ERROR) {
                    return onErrorReturn(
                        response.body.errorMessage,
                        shouldUseDialog = true,
                        shouldUseToast = false
                    )
                }

                val result1 = accountPropertiesDao.insertAndReplace(
                    AccountProperties(
                        response.body.data.id ?: 0,
                        response.body.data.email ?: "",
                        response.body.data.name ?: ""
                    )
                )
                // will return -1 if failure
                if (result1 < 0) {
                    onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_ACCOUNT_PROPERTIES, ResponseType.Dialog())
                        )
                    )
                    return
                }

                // will return -1 if failure
                val result2 = authTokenDao.insert(
                    AuthToken(
                        response.body.data.id ?: 0,
                        response.body.token
                    )
                )
                if (result2 < 0) {
                    onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_AUTH_TOKEN, ResponseType.Dialog())
                        )
                    )
                    return
                }

                saveAuthenticatedUserToPrefs(email)

                onCompleteJob(
                    DataState.data(
                        data = AuthViewState(
                            authToken = AuthToken(response.body.data.id ?: 0, response.body.token)
                        )
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<RegistrationResponse>> {
                return openApiAuthService.signupMemberRegister(
                    fname,
                    lname,
                    email,
                    country_id,
                    image,
                    phone,
                    password
                )
            }

            override fun setJob(job: Job) {
                addJob("attemptRegistration", job)
            }

        }.asLiveData()
    }


    fun attemptSignUpForNpo(
        name: String,
        cname: String,
        email: String,
        website: String,
        einNumber: String,
        phone: String,
        address: String,
        about: String,
        image: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>> {

        val registrationFieldErrors = SignUpAsNpoFirstPageFields(
            name,
            cname,
            email,
            website,
            einNumber,
            phone,
            address,
            about,
            password,
            confirmPassword
        ).isValidForRegistration()
        if (registrationFieldErrors != SignUpAsMemberFirstPageFields.RegistrationError.none()) {
            return returnErrorResponse(registrationFieldErrors, ResponseType.Dialog())
        }

        return object : NetworkBoundResource<RegistrationResponse, Any, AuthViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ) {
            // Ignore
            override fun loadFromCache(): LiveData<AuthViewState> {
                return AbsentLiveData.create()
            }

            // Ignore
            override suspend fun updateLocalDb(cacheObject: Any?) {

            }

            // not used in this case
            override suspend fun createCacheRequestAndReturn() {

            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<RegistrationResponse>) {

                Log.d(TAG, "handleApiSuccessResponse: $response")

                if (response.body.response == GENERIC_AUTH_ERROR) {
                    return onErrorReturn(
                        response.body.errorMessage,
                        shouldUseDialog = true,
                        shouldUseToast = false
                    )
                }

                val result1 = accountPropertiesDao.insertAndReplace(
                    AccountProperties(
                        response.body.data.id ?: 0,
                        response.body.data.email ?: "",
                        response.body.data.name ?: ""
                    )
                )
                // will return -1 if failure
                if (result1 < 0) {
                    onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_ACCOUNT_PROPERTIES, ResponseType.Dialog())
                        )
                    )
                    return
                }

                // will return -1 if failure
                val result2 = authTokenDao.insert(
                    AuthToken(
                        response.body.data.id ?: 0,
                        response.body.token
                    )
                )
                if (result2 < 0) {
                    onCompleteJob(
                        DataState.error(
                            Response(ERROR_SAVE_AUTH_TOKEN, ResponseType.Dialog())
                        )
                    )
                    return
                }

                saveAuthenticatedUserToPrefs(email)

                onCompleteJob(
                    DataState.data(
                        data = AuthViewState(
                            authToken = AuthToken(response.body.data.id ?: 0, response.body.token)
                        )
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<RegistrationResponse>> {
                return openApiAuthService.signupFundraiserRegister(
                    name,
                    cname,
                    email,
                    website,
                    einNumber,
                    phone,
                    address,
                    "1",
                    "",
                    about,
                    password,
                    "2",
                    "12"
                )
            }

            override fun setJob(job: Job) {
                addJob("attemptRegistration", job)
            }

        }.asLiveData()
    }

    fun checkPreviousAuthUser(): LiveData<DataState<AuthViewState>> {

        val previousAuthUserEmail: String? =
            sharedPreferences.getString(PreferenceKeys.PREVIOUS_AUTH_USER, null)

        if (previousAuthUserEmail.isNullOrBlank()) {
            Log.d(TAG, "checkPreviousAuthUser: No previously authenticated user found.")
            return returnNoTokenFound()
        } else {
            return object : NetworkBoundResource<Void, Any, AuthViewState>(
                sessionManager.isConnectedToTheInternet(),
                false,
                false,
                false
            ) {

                // Ignore
                override fun loadFromCache(): LiveData<AuthViewState> {
                    return AbsentLiveData.create()
                }

                // Ignore
                override suspend fun updateLocalDb(cacheObject: Any?) {

                }

                override suspend fun createCacheRequestAndReturn() {
                    accountPropertiesDao.searchByEmail(previousAuthUserEmail)
                        .let { accountProperties ->
                            Log.d(
                                TAG,
                                "createCacheRequestAndReturn: searching for token... account properties: $accountProperties"
                            )

                            accountProperties?.let {
                                if (accountProperties.pk > -1) {
                                    authTokenDao.searchByPk(accountProperties.pk).let { authToken ->
                                        if (authToken != null) {
                                            if (authToken.token != null) {
                                                onCompleteJob(
                                                    DataState.data(
                                                        AuthViewState(authToken = authToken)
                                                    )
                                                )
                                                return
                                            }
                                        }
                                    }
                                }
                            }
                            Log.d(TAG, "createCacheRequestAndReturn: AuthToken not found...")
                            onCompleteJob(
                                DataState.data(
                                    null,
                                    Response(
                                        RESPONSE_CHECK_PREVIOUS_AUTH_USER_DONE,
                                        ResponseType.None()
                                    )
                                )
                            )
                        }
                }

                // not used in this case
                override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<Void>) {
                }

                // not used in this case
                override fun createCall(): LiveData<GenericApiResponse<Void>> {
                    return AbsentLiveData.create()
                }

                override fun setJob(job: Job) {
                    addJob("checkPreviousAuthUser", job)
                }


            }.asLiveData()
        }
    }

    private fun saveAuthenticatedUserToPrefs(email: String) {
        sharedPrefsEditor.putString(PreferenceKeys.PREVIOUS_AUTH_USER, email)
        sharedPrefsEditor.apply()
    }

    private fun returnNoTokenFound(): LiveData<DataState<AuthViewState>> {
        return object : LiveData<DataState<AuthViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.data(
                    null,
                    Response(RESPONSE_CHECK_PREVIOUS_AUTH_USER_DONE, ResponseType.None())
                )
            }
        }
    }

    private fun returnErrorResponse(
        errorMessage: String,
        responseType: ResponseType
    ): LiveData<DataState<AuthViewState>> {
        Log.d(TAG, "returnErrorResponse: $errorMessage")

        return object : LiveData<DataState<AuthViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.error(
                    Response(
                        errorMessage,
                        responseType
                    )
                )
            }
        }
    }
}