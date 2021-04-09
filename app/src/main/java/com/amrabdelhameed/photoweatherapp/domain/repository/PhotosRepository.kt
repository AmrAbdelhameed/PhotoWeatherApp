package com.amrabdelhameed.photoweatherapp.domain.repository

import androidx.lifecycle.LiveData
import com.amrabdelhameed.photoweatherapp.data.PhotosDataSource
import com.amrabdelhameed.photoweatherapp.data.local.AppDatabase
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

class PhotosRepository(
    private val mAppDatabase: AppDatabase
) : BaseRepository(), PhotosDataSource {
    override fun getPhotos(): LiveData<List<Photo>> {
        return mAppDatabase.photoDao().getPhotos()
    }
}