package com.example.testtasksearchfortickets.presenter.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasksearchfortickets.data.model.Offer
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.domain.GetAllOffersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getAllOffers: GetAllOffersUseCase
) : ViewModel() {

    private val _offers = MutableLiveData<UiState<List<OfferUi>>>(UiState.Loading)

    val offers: LiveData<UiState<List<OfferUi>>>
        get() = _offers

    fun loadOffers() {
        viewModelScope.launch {
            val result = getAllOffers()
            _offers.postValue(UiState.fromDataState(result) {
                it.map {
                    it.toOfferUi()
                }
            })
        }
    }

}
