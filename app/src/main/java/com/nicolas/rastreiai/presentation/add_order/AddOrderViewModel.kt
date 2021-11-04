package com.nicolas.rastreiai.presentation.add_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.rastreiai.common.Resource
import com.nicolas.rastreiai.domain.model.InvalidOrderException
import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import com.nicolas.rastreiai.domain.use_cases.AddOrderUseCase
import com.nicolas.rastreiai.domain.use_cases.PostCodeOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrderViewModel @Inject constructor(
    private val addOrderUseCase: AddOrderUseCase,
    private val postCodeOrderUseCase: PostCodeOrderUseCase
) : ViewModel() {

    private val _orderState = MutableLiveData<OrderUiState>()
    val orderState: LiveData<OrderUiState> = _orderState

    fun insertOrderToDatabase(title: String, code: String) = viewModelScope.launch {
        try {
            addOrderUseCase.invoke(
                OrderEntity(
                    title = title,
                    code = code
                )
            )
        } catch (e: InvalidOrderException) {
            OrderUiState.Error(e.message.toString())
        }
    }

    fun postCodeOrder(code: String) {
        postCodeOrderUseCase(OrderRequest(code)).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _orderState.value = OrderUiState.Loading(isLoading = true)
                }
                is Resource.Success -> {
                    _orderState.value =
                        OrderUiState.Success(orderList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _orderState.value = OrderUiState.Error(
                        error = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class OrderUiState {
        class Loading(isLoading: Boolean = false) : OrderUiState()
        class Error(val error: String) : OrderUiState()
        class Success(val orderList: List<OrderStateUiDomain>) : OrderUiState()
    }
}