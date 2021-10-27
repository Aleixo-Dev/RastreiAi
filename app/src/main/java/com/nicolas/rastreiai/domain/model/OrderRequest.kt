package com.nicolas.rastreiai.domain.model

data class OrderRequest(
    val orderCode : String,
    val type : String = "LS",
)