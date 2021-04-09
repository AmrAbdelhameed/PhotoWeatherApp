package com.amrabdelhameed.photoweatherapp.data

import androidx.lifecycle.LiveData
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

interface PhotosDataSource {
    fun getPhotos(): LiveData<List<Photo>>
}