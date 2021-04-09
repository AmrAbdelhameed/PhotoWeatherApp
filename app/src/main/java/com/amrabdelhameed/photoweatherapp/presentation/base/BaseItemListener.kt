package com.amrabdelhameed.photoweatherapp.presentation.base

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}