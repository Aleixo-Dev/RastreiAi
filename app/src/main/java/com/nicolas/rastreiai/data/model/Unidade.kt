package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class Unidade(
    @SerializedName("cidade")
    val cidade: String,
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("endereco")
    val endereco: EnderecoX,
    @SerializedName("local")
    val local: String,
    @SerializedName("sto")
    val sto: String,
    @SerializedName("tipounidade")
    val tipounidade: String,
    @SerializedName("uf")
    val uf: String
)