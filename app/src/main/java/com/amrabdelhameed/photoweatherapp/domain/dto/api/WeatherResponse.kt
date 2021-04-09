package com.amrabdelhameed.photoweatherapp.domain.dto.api

data class WeatherResponse(
    val main: Main,
    val sys: Sys,
    val weather: List<Weather>,
)

data class Main(
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val country: String,
)

data class Weather(
    val description: String,
    val icon: String,
)