package com.nicolas.rastreiai.domain.model

data class OrderRequest(
    val code : String,
    val type : String = "LS",
)