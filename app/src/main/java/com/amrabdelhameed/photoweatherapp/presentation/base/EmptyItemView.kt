package com.amrabdelhameed.photoweatherapp.presentation.base

class EmptyItemView(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}