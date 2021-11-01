package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class Destino(
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("cidade")
    val cidade: String,
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("endereco")
    val endereco: Endereco,
    @SerializedName("local")
    val local: String,
    @SerializedName("uf")
    val uf: String
)