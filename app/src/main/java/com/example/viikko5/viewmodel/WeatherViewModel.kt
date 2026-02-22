package com.example.viikko5.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.viikko5.data.local.AppDatabase
import com.example.viikko5.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class WeatherUiState(
    val city: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

data class WeatherUiWeather(
    val city: String,
    val temp: Double,
    val description: String
)

class WeatherViewModel(app: Application) : AndroidViewModel(app) {

    private val db = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "weather_db"
    ).build()

    private val repository = WeatherRepository(db.weatherDao())

    // Room -> Flow -> UI
    private val latest = repository.observeWeather().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )

    val weather: StateFlow<WeatherUiWeather?> = latest
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )
        .let { flow ->
            MutableStateFlow<WeatherUiWeather?>(null).also { out ->
                viewModelScope.launch {
                    flow.collect { entity ->
                        out.value = entity?.let {
                            WeatherUiWeather(
                                city = it.city,
                                temp = it.temp,
                                description = it.description
                            )
                        }
                    }
                }
            }.asStateFlow()
        }

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun onCityChange(newCity: String) {
        _uiState.value = _uiState.value.copy(city = newCity)
    }

    fun fetchWeather() {
        val city = _uiState.value.city.trim()
        if (city.isBlank()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                repository.fetchAndSave(city)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error fetching weather"
                )
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}