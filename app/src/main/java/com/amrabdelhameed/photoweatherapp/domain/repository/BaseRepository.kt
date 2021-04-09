package com.amrabdelhameed.photoweatherapp.domain.repository

import android.util.Log
import com.amrabdelhameed.photoweatherapp.domain.dto.APIResult
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>
    ) = safeApiResult(call)

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): APIResult<T> {
        var response: Response<T>? = null
        try {
            response = call.invoke()
        } catch (ex: Exception) {
            Log.d("Network ex: ", ex.stackTrace.toString());ex.printStackTrace()
            return getResultError(response)
        }
        if (response.isSuccessful) return APIResult.Success(response.body()!!)
        return getResultError(response)
    }

    private fun <T> getResultError(response: Response<T>?) =
        APIResult.Error(errorBody = response?.errorBody(), statusCode = response?.code())

    protected fun <T : Any> getAPIResult(response: APIResult<T>): APIResult<T> {
        return when (response) {
            is APIResult.Success -> APIResult.Success(response.baseResponse)
            is APIResult.Error -> response
        }
    }
}