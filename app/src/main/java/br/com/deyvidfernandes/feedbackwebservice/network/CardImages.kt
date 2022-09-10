package br.com.deyvidfernandes.feedbackwebservice.network

import com.squareup.moshi.Json

data class CardImages (
            @Json(name = "small") val small: String = "",
            @Json(name = "large") val large: String = ""
        )