package com.nicolas.rastreiai.data.data_source.local.db

import androidx.room.*
import com.nicolas.rastreiai.domain.model.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders")
    fun getOrders() : Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders where id = :id")
    suspend fun getOrderById(id : Int) : OrderEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity)

    @Delete
    fun deleteOrder(orderEntity: OrderEntity)

}