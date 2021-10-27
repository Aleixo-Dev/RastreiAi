package com.nicolas.rastreiai.data.mapper

import com.nicolas.rastreiai.data.model.Objeto
import com.nicolas.rastreiai.data.model.PostmanResponse
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain

fun PostmanResponse.toOrderStateUiDomain(event: List<Objeto>): List<OrderStateUiDomain> {

    val orderState = ArrayList<OrderStateUiDomain>()

    for(description in event){
        val dataOrder = OrderStateUiDomain(
            description = description.evento[0].descricao
        )
        orderState.add(dataOrder)
    }
    return orderState
}