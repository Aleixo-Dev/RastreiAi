package com.nicolas.rastreiai.data.data_source.local

import com.nicolas.rastreiai.data.data_source.local.db.OrderDao
import com.nicolas.rastreiai.domain.model.OrderEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: OrderDao
) : LocalDataSource {

    override fun getOrders() = dao.getOrders()

    override suspend fun getOrderById(id: Int) = dao.getOrderById(id)

    override suspend fun insertOrder(orderEntity: OrderEntity) = dao.insertOrder(orderEntity)

    override suspend fun deleteOrder(orderEntity: OrderEntity) = dao.deleteOrder(orderEntity)

}