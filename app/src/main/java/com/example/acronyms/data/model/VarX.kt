package com.example.acronyms.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VarX(
    @Json(name = "freq")
    val freq: Int,
    @Json(name = "lf")
    val lf: String,
    @Json(name = "since")
    val since: Int
)