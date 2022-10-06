package com.example.acronyms.data.api

import com.example.acronyms.data.model.AbbreviationList
import com.example.acronyms.utils.DICTIONARY_ENDPOINT
import com.example.acronyms.utils.SF
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(DICTIONARY_ENDPOINT)
    suspend fun fetchAbbreviation(
        @Query(SF) sf: String
    ): Response<List<AbbreviationList>>
}
