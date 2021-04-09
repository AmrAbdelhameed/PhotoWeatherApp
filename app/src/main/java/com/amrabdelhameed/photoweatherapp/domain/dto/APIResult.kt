package com.amrabdelhameed.photoweatherapp.domain.dto

import okhttp3.ResponseBody

sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(
        val baseResponse: T
    ) : APIResult<T>()

    data class Error(
        val errorBody: ResponseBody? = null,
        val statusCode: Int? = null,
        val message: String? = null
    ) : APIResult<Nothing>()
}