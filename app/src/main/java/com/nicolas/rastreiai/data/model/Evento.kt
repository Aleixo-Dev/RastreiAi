package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class Evento(
    @SerializedName("cepDestino")
    val cepDestino: String,
    @SerializedName("criacao")
    val criacao: String,
    @SerializedName("data")
    val `data`: String,
    @SerializedName("dataPostagem")
    val dataPostagem: String,
    @SerializedName("descricao")
    val descricao: String,
    @SerializedName("destino")
    val destino: List<Destino>,
    @SerializedName("detalhe")
    val detalhe: String,
    @SerializedName("diasUteis")
    val diasUteis: String,
    @SerializedName("hora")
    val hora: String,
    @SerializedName("postagem")
    val postagem: Postagem,
    @SerializedName("prazoGuarda")
    val prazoGuarda: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("unidade")
    val unidade: Unidade
)