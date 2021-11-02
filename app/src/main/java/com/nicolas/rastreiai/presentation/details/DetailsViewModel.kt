package com.nicolas.rastreiai.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.rastreiai.common.Resource
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import com.nicolas.rastreiai.domain.use_cases.PostCodeOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val postCodeOrderUseCase: PostCodeOrderUseCase
) : ViewModel() {

    private val _responseCode = MutableLiveData<List<OrderStateUiDomain>>()
    val responseCode: LiveData<List<OrderStateUiDomain>> = _responseCode

    fun postCode(code: String) {
        postCodeOrderUseCase.invoke(OrderRequest(code)).onEach { state ->
            when(state){
                is Resource.Loading ->{

                }
                is Resource.Success ->{
                    _responseCode.value = state.data!!
                }
                is Resource.Error ->{

                }
            }

        }.launchIn(viewModelScope)
    }
}