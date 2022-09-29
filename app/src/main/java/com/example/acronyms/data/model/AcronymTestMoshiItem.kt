package com.example.acronyms.data.model

// @JsonClass(generateAdapter = true)
// data class AcronymTestMoshiItem(
//    @Json(name = "lfs")
//    val lfs: List<LfX>,
//    @Json(name = "sf")
//    val sf: String
// )
//data class AcronymResponse(val item: AcronymTestMoshiItem)
//data class AcronymTestMoshiItem(val a: List<AcronymList>)
data class AcronymList(val sf: String, val lfs: List<AcronymTestItem>)
data class AcronymTestItem(val lf: String)
// , val lfs: List<AcronymItem>)

// data class AcronymItem(val lf: String)
