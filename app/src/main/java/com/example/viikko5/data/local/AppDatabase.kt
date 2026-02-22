package com.example.viikko5.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.viikko5.data.model.WeatherCacheEntity

@Database(
    entities = [WeatherCacheEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}