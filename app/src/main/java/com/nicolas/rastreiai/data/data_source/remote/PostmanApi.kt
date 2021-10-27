package com.nicolas.rastreiai.data.data_source.remote

import com.nicolas.rastreiai.data.model.PostmanResponse
import com.nicolas.rastreiai.domain.model.OrderRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface PostmanApi {

    @POST("api/rastreio")
    suspend fun getOrderState(@Body orderRequest: OrderRequest) : PostmanResponse

}