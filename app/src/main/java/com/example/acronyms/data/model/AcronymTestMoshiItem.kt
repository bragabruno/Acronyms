package com.example.acronyms.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcronymTestMoshiItem(
    @Json(name = "lfs")
    val lfs: List<LfX>,
    @Json(name = "sf")
    val sf: String
)