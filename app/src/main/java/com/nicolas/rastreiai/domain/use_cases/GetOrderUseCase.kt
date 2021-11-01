package com.nicolas.rastreiai.domain.use_cases

import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val repository: PostmanRepository
) {

    operator fun invoke(): Flow<List<OrderEntity>> {
        return repository.getOrders()
    }
}