package com.example.testtasksearchfortickets.presenter.selectCountry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.domain.GetTicketsOffersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectCountryViewModel @Inject constructor(
    private val getTicketsOffers: GetTicketsOffersUseCase
) : ViewModel() {

    private val _ticketsOffers = MutableLiveData<UiState<List<OfferedTicketUi>>>(UiState.Loading)

    val ticketsOffers: LiveData<UiState<List<OfferedTicketUi>>>
        get() = _ticketsOffers


    fun loadOffers() {
        viewModelScope.launch {
            val result = getTicketsOffers()
            _ticketsOffers.postValue(UiState.fromDataState(result) {
                it.map {
                    it.toOfferedTicketUi()
                }
            })
        }
    }
}
