package com.nicolas.rastreiai.data.repository

import com.nicolas.rastreiai.data.data_source.local.LocalDataSource
import com.nicolas.rastreiai.data.data_source.remote.PostmanRemoteDataSource
import com.nicolas.rastreiai.data.mapper.toOrderStateUiDomain
import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostmanRepositoryImpl @Inject constructor(
    private val remote: PostmanRemoteDataSource,
    private val local: LocalDataSource
) : PostmanRepository {

    override suspend fun getOrderState(orderRequest: OrderRequest): List<OrderStateUiDomain> {
        val response = remote.getOrderState(orderRequest)
        return response.let {
            it.toOrderStateUiDomain(it.objeto[0].evento, it.objeto)
        }
    }

    override fun getOrders(): Flow<List<OrderEntity>> {
        return local.getOrders()
    }

    override suspend fun getOrderById(id: Int): OrderEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun insertOrder(note: OrderEntity) {
        local.insertOrder(note)
    }

    override suspend fun deleteOrder(order: OrderEntity) {
        local.deleteOrder(order)
    }
}