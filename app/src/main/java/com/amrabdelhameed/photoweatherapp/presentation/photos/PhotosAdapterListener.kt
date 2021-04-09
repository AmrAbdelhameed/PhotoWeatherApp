package com.amrabdelhameed.photoweatherapp.presentation.photos

import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseItemListener

interface PhotosAdapterListener : BaseItemListener<Photo> {
    override fun onRetryClick() {}
}