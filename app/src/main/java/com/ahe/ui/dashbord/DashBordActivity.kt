package com.ahe.ui.dashbord

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ahe.R
import com.ahe.ui.BaseActivity
import javax.inject.Inject

class DashBordActivity:BaseActivity(){
    override fun displayProgressBar(bool: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun expandAppBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var viewModel: DashBosrdViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)

        viewModel = ViewModelProvider(this, providerFactory).get(DashBosrdViewModel::class.java)
        //findNavController(R.id.auth_nav_host_fragment).addOnDestinationChangedListener(this)

       // subscribeObservers()
    }
}