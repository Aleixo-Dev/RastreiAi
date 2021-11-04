package com.nicolas.rastreiai.domain.repository

import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import kotlinx.coroutines.flow.Flow

interface PostmanRepository {

    suspend fun getOrderState(orderRequest: OrderRequest) : List<OrderStateUiDomain>

    fun getOrders() : Flow<List<OrderEntity>>

    suspend fun getOrderById(id: Int): OrderEntity?

    suspend fun insertOrder(note: OrderEntity)

    suspend fun deleteOrder(order: OrderEntity)

}