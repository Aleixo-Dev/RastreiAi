package com.nicolas.rastreiai.data.data_source.remote

import com.nicolas.rastreiai.data.model.PostmanResponse
import com.nicolas.rastreiai.domain.model.OrderRequest

interface PostmanRemoteDataSource {

    suspend fun getOrderState(orderRequest: OrderRequest) : PostmanResponse

}