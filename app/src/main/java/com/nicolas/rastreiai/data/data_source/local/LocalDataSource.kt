package com.nicolas.rastreiai.data.data_source.local

import com.nicolas.rastreiai.domain.model.OrderEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getOrders() : Flow<List<OrderEntity>>

    suspend fun getOrderById(id: Int): OrderEntity?

    suspend fun insertOrder(orderEntity: OrderEntity)

    suspend fun deleteOrder(orderEntity: OrderEntity)

}