package br.com.deyvidfernandes.feedbackwebservice.network

import com.squareup.moshi.Json

class CardAttacks (
    @Json(name = "name") val name: String = "",
    @Json(name = "cost") val cost: List<String> = listOf(),
    @Json(name = "convertedEnergyCost") val convertedEnergyCost: Int = 0,
    @Json(name = "damage") val damage: String = "",
    @Json(name = "text") val text: String = ""
)