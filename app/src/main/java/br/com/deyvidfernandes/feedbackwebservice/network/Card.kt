package br.com.deyvidfernandes.feedbackwebservice.network

import com.squareup.moshi.Json

data class Card (
            val id: String,
            @Json(name = "name") val name: String,
            @Json(name = "supertype") val supertype: String = "",
            @Json(name = "level") val level: String = "",
            @Json(name = "hp") val hp: String = "",
            @Json(name = "images") val images: CardImages,
            @Json(name = "attacks") val attacks: List<CardAttacks>,
            @Json(name = "subtypes") val subtypes: List<String> = listOf(),
            @Json(name = "types") val types: List<String> = listOf(),
            @Json(name = "rules") val rules: List<String> = listOf()
        )