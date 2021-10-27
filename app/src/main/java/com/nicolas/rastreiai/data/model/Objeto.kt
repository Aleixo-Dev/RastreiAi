package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class Objeto(
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("evento")
    val evento: List<Evento>,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("numero")
    val numero: String,
    @SerializedName("sigla")
    val sigla: String
)