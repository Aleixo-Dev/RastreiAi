package com.nicolas.rastreiai.data.repository

import com.nicolas.rastreiai.data.data_source.remote.PostmanRemoteDataSource
import com.nicolas.rastreiai.data.mapper.toOrderStateUiDomain
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import javax.inject.Inject

class PostmanRepositoryImpl @Inject constructor(
    private val remote : PostmanRemoteDataSource
) : PostmanRepository{

    override suspend fun getOrderState(orderRequest: OrderRequest): List<OrderStateUiDomain> {
        val response = remote.getOrderState(orderRequest)
        return response.let {
            it.toOrderStateUiDomain(it.objeto)
        }
    }
}