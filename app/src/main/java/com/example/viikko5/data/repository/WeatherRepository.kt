package com.example.viikko5.data.repository

import com.example.viikko5.BuildConfig
import com.example.viikko5.data.local.WeatherDao
import com.example.viikko5.data.model.WeatherCacheEntity
import com.example.viikko5.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class WeatherRepository(
    private val dao: WeatherDao
) {

    fun observeWeather(): Flow<WeatherCacheEntity?> =
        dao.getWeather()

    suspend fun fetchAndSave(city: String) {

        val current = dao.getWeatherOnce()

        val now = System.currentTimeMillis()
        val thirtyMinutes = 30 * 60 * 1000

        if (current != null &&
            current.city == city &&
            now - current.timestamp < thirtyMinutes
        ) {
            return
        }

        val response = RetrofitInstance.api.getWeather(
            city = city,
            apiKey = BuildConfig.OPENWEATHER_API_KEY,
            units = "metric"
        )

        val entity = WeatherCacheEntity(
            city = city,
            temp = response.main.temp,
            description = response.weather.firstOrNull()?.description ?: "",
            timestamp = now
        )

        dao.insertWeather(entity)
    }
}