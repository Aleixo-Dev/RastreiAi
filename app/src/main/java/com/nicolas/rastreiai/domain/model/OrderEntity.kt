package com.nicolas.rastreiai.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val code: String
) : Serializable

class InvalidOrderException(message: String) : Exception(message)