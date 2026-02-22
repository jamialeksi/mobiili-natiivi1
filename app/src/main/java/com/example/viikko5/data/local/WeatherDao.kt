package com.example.viikko5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viikko5.data.model.WeatherCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_cache WHERE id = 1")
    fun getWeather(): Flow<WeatherCacheEntity?>

    @Query("SELECT * FROM weather_cache WHERE id = 1")
    suspend fun getWeatherOnce(): WeatherCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherCacheEntity)
}