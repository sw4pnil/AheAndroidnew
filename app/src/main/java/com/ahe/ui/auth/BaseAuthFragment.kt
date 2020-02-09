package com.ahe.ui.auth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahe.di.Injectable
import com.ahe.ui.DataStateChangeListener
import com.ahe.ui.UICommunicationListener
import com.ahe.ui.main.blog.state.BLOG_VIEW_STATE_BUNDLE_KEY
import com.ahe.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

abstract class BaseAuthFragment : Fragment(),
    Injectable {

    val TAG: String = "AppDebug"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var uiCommunicationListener: UICommunicationListener

    lateinit var stateChangeListener: DataStateChangeListener

    lateinit var viewModel: AuthViewModel

    fun isViewModelInitialized() = ::viewModel.isInitialized

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        cancelActiveJobs()
    }

    private fun cancelActiveJobs() {
        viewModel.cancelActiveJobs()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiCommunicationListener = context as UICommunicationListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "$context must implement UICommunicationListener")
        }

        try{
            stateChangeListener = context as DataStateChangeListener
        }catch(e: ClassCastException){
            Log.e(TAG, "$context must implement DataStateChangeListener" )
        }

    }

}



























