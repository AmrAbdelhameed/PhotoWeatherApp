package com.amrabdelhameed.photoweatherapp.presentation.photo_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amrabdelhameed.photoweatherapp.data.PhotoDetailsDataSource
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseViewModel
import com.amrabdelhameed.photoweatherapp.presentation.base.NavigationCommand
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val photoDetailsDataSource: PhotoDetailsDataSource
) : BaseViewModel() {
    val photoPath: MutableLiveData<String> = MutableLiveData()

    fun deletePhoto(photo: Photo) {
        viewModelScope.launch {
            photoDetailsDataSource.delete(photo)
            navigationCommand.value = NavigationCommand.Back
        }
    }
}