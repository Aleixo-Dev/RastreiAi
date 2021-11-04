package com.nicolas.rastreiai.domain.use_cases

import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(
    private val repository: PostmanRepository
) {
    suspend operator fun invoke(orderEntity: OrderEntity){
        repository.deleteOrder(orderEntity)
    }
}