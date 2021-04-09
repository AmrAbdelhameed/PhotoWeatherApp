package com.amrabdelhameed.photoweatherapp.domain.repository

import com.amrabdelhameed.photoweatherapp.data.PhotoDetailsDataSource
import com.amrabdelhameed.photoweatherapp.data.local.AppDatabase
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

class PhotoDetailsRepository(
    private val mAppDatabase: AppDatabase
) : BaseRepository(), PhotoDetailsDataSource {
    override suspend fun delete(photo: Photo) {
        mAppDatabase.photoDao().delete(photo)
    }
}