package com.example.acronyms.data.model

data class AbbreviationList(
    val sf: String? = null,
    val lfs: List<AbbreviationItem>
)

data class AbbreviationItem(
    val lf: String? = null,
    val freq: Int? = null,
    val since: Int? = null,
    val vars: List<AbbreviationItem?>? = null
)
