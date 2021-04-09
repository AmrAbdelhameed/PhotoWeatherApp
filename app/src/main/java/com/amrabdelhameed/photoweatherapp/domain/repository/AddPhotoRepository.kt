package com.amrabdelhameed.photoweatherapp.domain.repository

import com.amrabdelhameed.photoweatherapp.data.AddPhotoDataSource
import com.amrabdelhameed.photoweatherapp.data.local.AppDatabase
import com.amrabdelhameed.photoweatherapp.data.remote.WeatherApiService
import com.amrabdelhameed.photoweatherapp.domain.dto.APIResult
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import okhttp3.ResponseBody

class AddPhotoRepository(
    private val weatherApiService: WeatherApiService,
    private val mAppDatabase: AppDatabase
) : BaseRepository(), AddPhotoDataSource {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): APIResult<ResponseBody> {
        return getAPIResult(safeApiCall {
            weatherApiService.getCurrentWeather(
                latitude,
                longitude
            )
        })
    }

    override suspend fun insert(photo: Photo) {
        mAppDatabase.photoDao().insert(photo)
    }
}