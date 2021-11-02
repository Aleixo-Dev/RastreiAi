package com.nicolas.rastreiai.data.mapper

import com.nicolas.rastreiai.data.model.Evento
import com.nicolas.rastreiai.data.model.Objeto
import com.nicolas.rastreiai.data.model.PostmanResponse
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain

fun PostmanResponse.toOrderStateUiDomain(
    event: List<Evento>?,
    listObject: List<Objeto>
): List<OrderStateUiDomain> {

    val orderState = ArrayList<OrderStateUiDomain>()

    if (event != null) {
        for (order in event) {
            for (orderObject in listObject) {
                val dataOrder = OrderStateUiDomain(
                    description = order.descricao,
                    code = orderObject.numero,
                    category = orderObject.categoria,
                    date = order.data
                )
                orderState.add(dataOrder)
            }
        }
    }
    return orderState
}