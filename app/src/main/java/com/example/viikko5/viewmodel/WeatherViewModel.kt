package com.example.viikko5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viikko5.BuildConfig
import com.example.viikko5.data.model.WeatherResponse
import com.example.viikko5.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val city: String = "",
    val weather: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun onCityChange(newCity: String) {
        _uiState.value = _uiState.value.copy(city = newCity)
    }

    fun fetchWeather() {
        val city = _uiState.value.city
        if (city.isBlank()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                val result = RetrofitInstance.api.getWeather(
                    city,
                    BuildConfig.OPENWEATHER_API_KEY
                )

                _uiState.value = _uiState.value.copy(
                    weather = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error, not a real city kekw"
                )
            }
        }
    }
}
