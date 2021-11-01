package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class Postagem(
    @SerializedName("ar")
    val ar: String,
    @SerializedName("cepdestino")
    val cepdestino: String,
    @SerializedName("datapostagem")
    val datapostagem: String,
    @SerializedName("dataprogramada")
    val dataprogramada: String,
    @SerializedName("dh")
    val dh: String,
    @SerializedName("mp")
    val mp: String,
    @SerializedName("peso")
    val peso: String,
    @SerializedName("prazotratamento")
    val prazotratamento: String,
    @SerializedName("volume")
    val volume: String
)