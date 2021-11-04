package com.nicolas.rastreiai.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.use_cases.DeleteItemUseCase
import com.nicolas.rastreiai.domain.use_cases.GetOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    init {
        getOrders()
    }

    private val _orders = MutableLiveData<List<OrderEntity>>()
    val orders: LiveData<List<OrderEntity>> = _orders

    private fun getOrders() {
        getOrderUseCase.invoke().onEach {
            _orders.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteOrder(orderEntity: OrderEntity) = viewModelScope.launch {
        deleteItemUseCase.invoke(orderEntity)
    }
}