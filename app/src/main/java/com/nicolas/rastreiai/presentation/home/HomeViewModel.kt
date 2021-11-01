package com.nicolas.rastreiai.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import com.nicolas.rastreiai.domain.use_cases.GetOrderUseCase
import com.nicolas.rastreiai.domain.use_cases.PostCodeOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postCodeOrderUseCase: PostCodeOrderUseCase,
    private val getOrderUseCase: GetOrderUseCase
) : ViewModel() {

    init {
        getOrders()
    }

    private val _responseCode = MutableLiveData<List<OrderStateUiDomain>>()
    val responseCode: LiveData<List<OrderStateUiDomain>> = _responseCode

    private val _orders = MutableLiveData<List<OrderEntity>>()
    val orders : LiveData<List<OrderEntity>> = _orders

    fun sendCodeToApi(orderRequest: OrderRequest) = viewModelScope.launch {
        postCodeOrderUseCase.invoke(orderRequest)
    }

    private fun getOrders(){
        getOrderUseCase.invoke().onEach {
            _orders.value = it
        }.launchIn(viewModelScope)
    }
}