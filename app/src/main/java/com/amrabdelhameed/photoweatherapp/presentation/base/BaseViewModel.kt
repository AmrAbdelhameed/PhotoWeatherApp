package com.amrabdelhameed.photoweatherapp.presentation.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.amrabdelhameed.photoweatherapp.domain.dto.APIResult
import com.amrabdelhameed.photoweatherapp.utils.SingleLiveEvent
import kotlin.reflect.KClass

abstract class BaseViewModel : ViewModel() {
    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val activityToStart = SingleLiveEvent<Pair<KClass<*>, Bundle?>>()
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()

    fun handleErrorResponse(result: APIResult.Error) {
        try {
            showToast.value = result.message
        } catch (ex: Exception) {
            showToast.value = ex.localizedMessage
        }
    }
}