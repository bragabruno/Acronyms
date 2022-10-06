package com.example.acronyms.utils

import com.example.acronyms.data.model.AbbreviationList

sealed class ApiState {
    object Loading : ApiState()
    class Error(val exception: Exception) : ApiState()
    class Success(val response: AbbreviationList) : ApiState()
}
