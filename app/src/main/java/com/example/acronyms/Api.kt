package com.example.acronyms

import com.example.acronyms.data.model.AcronymTestMoshiItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("software/acromine/dictionary.py")
    fun getAcronyms(
        @Query("sf") sf: String
    ): Response<AcronymTestMoshiItem>
}

