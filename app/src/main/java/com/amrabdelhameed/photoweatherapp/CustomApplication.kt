package com.amrabdelhameed.photoweatherapp

import androidx.multidex.MultiDexApplication
import com.amrabdelhameed.photoweatherapp.data.*
import com.amrabdelhameed.photoweatherapp.data.local.Database
import com.amrabdelhameed.photoweatherapp.data.remote.Api
import com.amrabdelhameed.photoweatherapp.domain.repository.*
import com.amrabdelhameed.photoweatherapp.presentation.MainViewModel
import com.amrabdelhameed.photoweatherapp.presentation.add_photo.AddPhotoViewModel
import com.amrabdelhameed.photoweatherapp.presentation.photo_details.PhotoDetailsViewModel
import com.amrabdelhameed.photoweatherapp.presentation.photos.PhotosViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CustomApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        val myModule = module {
            single { Api.Companion.weatherApiService() }
            single { Database.Companion.appDatabase(get()) }

            viewModel { MainViewModel() }
            viewModel { PhotosViewModel(get()) }
            viewModel { AddPhotoViewModel(get()) }
            viewModel { PhotoDetailsViewModel(get()) }

            single<PhotosDataSource> { PhotosRepository(get()) }
            single<AddPhotoDataSource> { AddPhotoRepository(get(), get()) }
            single<PhotoDetailsDataSource> { PhotoDetailsRepository(get()) }
        }

        startKoin {
            androidContext(this@CustomApplication)
            modules(listOf(myModule))
        }
    }
}