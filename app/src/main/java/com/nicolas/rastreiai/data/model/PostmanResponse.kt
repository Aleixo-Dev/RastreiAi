package com.nicolas.rastreiai.data.model


import com.google.gson.annotations.SerializedName

data class PostmanResponse(
    @SerializedName("objeto")
    val objeto: List<Objeto>,
    @SerializedName("pesquisa")
    val pesquisa: String,
    @SerializedName("quantidade")
    val quantidade: String,
    @SerializedName("resultado")
    val resultado: String,
    @SerializedName("versao")
    val versao: String
)