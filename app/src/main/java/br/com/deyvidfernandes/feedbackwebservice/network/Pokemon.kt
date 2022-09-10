package br.com.deyvidfernandes.feedbackwebservice.network

import com.squareup.moshi.Json

data class Pokemon (
            val data: List<Card>,
            @Json(name = "page") val page: Int,
            @Json(name = "pageSize") val pageSize: Int,
            @Json(name = "count") val count: Int,
            @Json(name = "totalCount") val totalCount: Int
        )