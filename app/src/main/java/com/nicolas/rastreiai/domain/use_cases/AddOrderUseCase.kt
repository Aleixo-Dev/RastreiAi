package com.nicolas.rastreiai.domain.use_cases

import com.nicolas.rastreiai.domain.model.InvalidOrderException
import com.nicolas.rastreiai.domain.model.OrderEntity
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import javax.inject.Inject
import kotlin.Throws

class AddOrderUseCase @Inject constructor(
    private val repository: PostmanRepository
) {

    @Throws(InvalidOrderException::class)
    suspend operator fun invoke(orderEntity: OrderEntity){
        if(orderEntity.title.isBlank()){
            throw InvalidOrderException("The title of the note can't be empty.")
        }
        if(orderEntity.code.isBlank()){
            throw InvalidOrderException("The code of the order can't be empty.")
        }
        repository.insertOrder(orderEntity)
    }
}