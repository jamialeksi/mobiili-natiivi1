package com.example.viikko5.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_cache")
data class WeatherCacheEntity(
    @PrimaryKey val id: Int = 1,
    val city: String,
    val temp: Double,
    val description: String,
    val timestamp: Long
)