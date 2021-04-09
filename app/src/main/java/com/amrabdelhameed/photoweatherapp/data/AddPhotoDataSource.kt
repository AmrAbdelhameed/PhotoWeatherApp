package com.amrabdelhameed.photoweatherapp.data

import com.amrabdelhameed.photoweatherapp.domain.dto.APIResult
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import okhttp3.ResponseBody

interface AddPhotoDataSource {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): APIResult<ResponseBody>
    suspend fun insert(photo: Photo)
}