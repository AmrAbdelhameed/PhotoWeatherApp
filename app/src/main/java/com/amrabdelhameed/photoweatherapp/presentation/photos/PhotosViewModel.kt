package com.amrabdelhameed.photoweatherapp.presentation.photos

import androidx.lifecycle.LiveData
import com.amrabdelhameed.photoweatherapp.data.PhotosDataSource
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseViewModel

class PhotosViewModel(
    photoDataSource: PhotosDataSource
) : BaseViewModel() {
    val photosLiveData: LiveData<List<Photo>> = photoDataSource.getPhotos()
}