package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class Endereco(
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("cep")
    val cep: String,
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("localidade")
    val localidade: String,
    @SerializedName("logradouro")
    val logradouro: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("numero")
    val numero: String,
    @SerializedName("uf")
    val uf: String
)