package com.amrabdelhameed.photoweatherapp.presentation.photos

import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

class PhotoItemView(
    private val listener: PhotosAdapterListener
) {
    fun onItemClick(item: Photo) = listener.onItemClick(item)
}