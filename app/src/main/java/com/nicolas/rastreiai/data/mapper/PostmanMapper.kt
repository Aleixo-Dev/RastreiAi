package com.nicolas.rastreiai.data.mapper

import com.nicolas.rastreiai.data.model.Evento
import com.nicolas.rastreiai.data.model.Objeto
import com.nicolas.rastreiai.data.model.PostmanResponse
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain

fun PostmanResponse.toOrderStateUiDomain(event: List<Objeto>): List<OrderStateUiDomain> {

    val orderState = ArrayList<OrderStateUiDomain>()

    for (order in event) {
        val dataOrder = OrderStateUiDomain(
            description = order.categoria,
            code = order.numero,
            category = order.categoria
        )
        orderState.add(dataOrder)
    }
    return orderState
}