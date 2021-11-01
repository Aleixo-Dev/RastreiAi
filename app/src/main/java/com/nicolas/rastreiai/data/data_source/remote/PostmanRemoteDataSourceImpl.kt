package com.nicolas.rastreiai.data.data_source.remote

import com.nicolas.rastreiai.domain.model.OrderRequest
import javax.inject.Inject

class PostmanRemoteDataSourceImpl @Inject constructor(
    private val api: PostmanApi
) : PostmanRemoteDataSource {

    override suspend fun getOrderState(orderRequest: OrderRequest) =
        api.getOrderState(orderRequest)
}