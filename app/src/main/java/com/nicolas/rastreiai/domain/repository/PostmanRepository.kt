package com.nicolas.rastreiai.domain.repository

import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain

interface PostmanRepository {

    suspend fun getOrderState(orderRequest: OrderRequest) : List<OrderStateUiDomain>

}