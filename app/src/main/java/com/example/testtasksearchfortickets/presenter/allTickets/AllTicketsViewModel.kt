package com.example.testtasksearchfortickets.presenter.allTickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.domain.GetAllTicketsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTicketsViewModel @Inject constructor(
    private val getAllTickets: GetAllTicketsUseCase
) : ViewModel() {

    private val _tickets = MutableLiveData<UiState<List<TicketUi>>>(UiState.Loading)
    val tickets: LiveData<UiState<List<TicketUi>>>
        get() = _tickets


    fun loadTickets() {
        viewModelScope.launch {
            val result = getAllTickets()
            _tickets.postValue(UiState.fromDataState(result) {
                it.map {
                    it.toTicketUi()
                }
            })
        }
    }

}
