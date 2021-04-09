package com.amrabdelhameed.photoweatherapp.presentation.add_photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amrabdelhameed.photoweatherapp.data.AddPhotoDataSource
import com.amrabdelhameed.photoweatherapp.domain.dto.APIResult
import com.amrabdelhameed.photoweatherapp.domain.dto.api.WeatherResponse
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseViewModel
import com.amrabdelhameed.photoweatherapp.presentation.base.NavigationCommand
import com.amrabdelhameed.photoweatherapp.utils.getCurrentDate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class AddPhotoViewModel(
    private val addPhotoDataSource: AddPhotoDataSource
) : BaseViewModel() {
    val weatherDataItem: MutableLiveData<WeatherDataItem> = MutableLiveData()
    val isWeatherEnabled: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var address: String

    fun getCurrentWeather(latitude: Double, longitude: Double) {
        isLoading.value = true
        viewModelScope.launch {
            when (val result = addPhotoDataSource.getCurrentWeather(latitude, longitude)) {
                is APIResult.Success -> handleSuccessResponse(result)
                is APIResult.Error -> handleErrorResponse(result)
            }
            isLoading.value = false
        }
    }

    private fun handleSuccessResponse(result: APIResult.Success<ResponseBody>) {
        try {
            val type = object : TypeToken<WeatherResponse>() {}.type
            val baseResponse = Gson().fromJson<WeatherResponse>(
                result.baseResponse.string(),
                type
            )
            isWeatherEnabled.value = true
            weatherDataItem.value = WeatherDataItem(
                (baseResponse.main.temp.toInt()).toString(),
                (baseResponse.main.temp_max.toInt()).toString(),
                (baseResponse.main.temp_min.toInt()).toString(),
                baseResponse.weather[0].description,
                getCurrentDate(),
                baseResponse.sys.country,
                address
            )
        } catch (ex: Exception) {
            showToast.value = ex.localizedMessage
            return
        }
    }

    fun insertPhoto(filePath: String) {
        viewModelScope.launch {
            addPhotoDataSource.insert(Photo(filePath))
            navigationCommand.value = NavigationCommand.Back
        }
    }
}