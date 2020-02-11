package com.ahe.ui.dashbord

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ahe.models.AuthToken
import com.ahe.repository.dashboard.DashboardRepository
import com.ahe.ui.BaseViewModel
import com.ahe.ui.DataState
import com.ahe.ui.dashbord.state.DashBoardStateEvent
import com.ahe.ui.dashbord.state.DashBoardViewState
import javax.inject.Inject

class DashBoardViewModel
@Inject
constructor(
    private val dashboardRepository: DashboardRepository
) : BaseViewModel<DashBoardStateEvent, DashBoardViewState>() {
    override fun handleStateEvent(stateEvent: DashBoardStateEvent): LiveData<DataState<DashBoardViewState>> {
        return when (stateEvent) {

            is DashBoardStateEvent.None -> {
                liveData {
                }
            }
        }
    }

    override fun initNewViewState(): DashBoardViewState {
        return DashBoardViewState()
    }


    fun cancelActiveJobs() {
        handlePendingData()
        dashboardRepository.cancelActiveJobs()
    }

    private fun handlePendingData() {
        setStateEvent(DashBoardStateEvent.None())
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }

    fun setAuthToken(authToken: AuthToken) {
        val update = getCurrentViewStateOrNew()
        if (update.authToken == authToken) {
            return
        }
        update.authToken = authToken
        setViewState(update)
    }
}