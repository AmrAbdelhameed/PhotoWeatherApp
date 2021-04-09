package com.amrabdelhameed.photoweatherapp.data

import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

interface PhotoDetailsDataSource {
    suspend fun delete(photo: Photo)
}