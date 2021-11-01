package com.nicolas.rastreiai.data.data_source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicolas.rastreiai.domain.model.OrderEntity

@Database(
    entities = [OrderEntity::class],
    version = 1
)
abstract class OrderDatabase : RoomDatabase() {

    abstract val orderDao : OrderDao

    companion object{
        const val DATABASE_NAME = "order_db"
    }

}