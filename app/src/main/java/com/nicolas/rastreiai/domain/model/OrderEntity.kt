package com.nicolas.rastreiai.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val code: String
)

class InvalidOrderException(message: String) : Exception(message)