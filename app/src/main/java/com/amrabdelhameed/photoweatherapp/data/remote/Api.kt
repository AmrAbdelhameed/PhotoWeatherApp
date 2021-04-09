package com.amrabdelhameed.photoweatherapp.data.remote

import com.amrabdelhameed.photoweatherapp.BuildConfig
import com.amrabdelhameed.photoweatherapp.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
                val url = it.url.newBuilder()
                    .addQueryParameter(AppConstants.API_KEY, BuildConfig.API_KEY)
                    .addQueryParameter(AppConstants.UNITS_KEY, AppConstants.UNITS)
                    .build()
                val newRequest = it.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)
            }
        }).addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    object Companion {
        fun weatherApiService(): WeatherApiService = retrofit()
            .create(WeatherApiService::class.java)
    }
}